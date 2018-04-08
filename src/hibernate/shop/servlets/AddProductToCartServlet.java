package hibernate.shop.servlets;

import hibernate.shop.cart.Cart;
import hibernate.shop.cart.CartDetail;
import hibernate.shop.cart.CartRepository;
import hibernate.shop.product.Product;
import hibernate.shop.product.ProductRepository;
import hibernate.shop.user.User;
import hibernate.shop.utils.ProjectHelper;
import hibernate.shop.utils.UserSessionHelper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;

public class AddProductToCartServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        User user = UserSessionHelper.getUserFromCookie(req.getCookies());

        Long productId = ProjectHelper.parseStringToLong(req.getParameter("productId"));
        BigDecimal productAmount = ProjectHelper.parseStringToBigDecimal(req.getParameter("productAmount"));

        if (productId > 0 && productAmount.compareTo(BigDecimal.ZERO) == 1 && user != null) {


            Optional<Cart> byUserId = CartRepository.findByUserId(user.getId());
            if (byUserId.isPresent()) {
                Cart cart = byUserId.get();
                Optional<CartDetail> productInCart = cart.getCartDetailSet().stream()
                        .filter(cd -> cd.getProduct().getId().equals(productId))
                        .findFirst();
                if (productInCart.isPresent()) {
                    //produkt jest juz w koszyku
                    productInCart.get().setAmount(productInCart.get().getAmount().add(productAmount));
                    CartRepository.saveCart(cart);
                } else {
                    boolean result = createNwCartDetail(productId, productAmount, cart);
                    if (result) {
                        CartRepository.saveCart(cart);
                        writer.write("Dodano produkt z id " + productId + " do koszyka");
                    }
                }

            } else {
                Cart cart = new Cart();
                cart.setUser(user);
                cart.setCartDetailSet(new HashSet<>());
                boolean result = createNwCartDetail(productId, productAmount, cart);
                if (result) {
                    CartRepository.saveCart(cart);
                    writer.write("Dodano produkt z id " + productId + " do koszyka");
                } else {
                    writer.write("Nie ma takiego produktu");
                }


            }
        } else {
            if (productId == 0) {
                writer.write("Nie ma takiego produktu");
            }
            if (productAmount.compareTo(BigDecimal.ZERO) <= 0) {
                writer.write("Nie mozna dodac produktu z iloscia ujemna badz zerowa");
            } else {
                writer.write("Nie ma takiego produktu");
            }
            if(user==null){
                writer.write("Prosze sie zalogowac");
            }
        }
    }




    private boolean createNwCartDetail(Long productId, BigDecimal productAmount, Cart cart) {
        Optional<Product> oneById = ProductRepository.findOneById(productId);
        if (oneById.isPresent()) {
            CartDetail cartDetail = new CartDetail();
            cartDetail.setAmount(productAmount);
            cartDetail.setProduct(oneById.get());
            cartDetail.setPrice(oneById.get().getPrice());
            cart.addCartDetail(cartDetail);
            return true;
        }
        return false;
    }
}
