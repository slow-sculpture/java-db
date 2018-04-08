package hibernate.shop.servlets;

import hibernate.shop.cart.Cart;
import hibernate.shop.cart.CartDetail;
import hibernate.shop.cart.CartRepository;
import hibernate.shop.user.User;
import hibernate.shop.utils.ProjectHelper;
import hibernate.shop.utils.UserSessionHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@WebServlet(name = "DecreaseProductAmountServlet", urlPatterns = "/decreaseProductAmount")
public class DecreaseProductAmountServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long productId = ProjectHelper.parseStringToLong(req.getParameter("productId"));
        String method = req.getParameter("method");

        User user = UserSessionHelper.getUserFromCookie(req.getCookies());
        if (user != null) {
            Optional<Cart> byUserId = CartRepository.findByUserId(user.getId());
            if (byUserId.isPresent()) {
                Optional<CartDetail> cartDetail = byUserId.get().getCartDetailSet().stream()
                        .filter(cd -> cd.getProduct().getId().equals(productId)).findFirst();
                if (cartDetail.isPresent()) {
                    CartDetail cd = cartDetail.get();
                    if (method.equals("add")) {
                        cd.setAmount(cd.getAmount().add(BigDecimal.ONE));
                    } else if (method.equals("substract")) {
                        if (cd.getAmount().compareTo(BigDecimal.ONE) < 1) {
                            //usuwanie produktu
                            byUserId.get().getCartDetailSet().remove(cd);
                        } else {
                            cd.setAmount(cd.getAmount().subtract(BigDecimal.ONE));
                        }
                    }
                    CartRepository.saveCart(byUserId.get());
                }
            }
        }
    }
}
