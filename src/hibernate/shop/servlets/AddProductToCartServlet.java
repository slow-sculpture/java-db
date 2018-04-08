package hibernate.shop.servlets;

import hibernate.shop.cart.Cart;
import hibernate.shop.cart.CartDetail;
import hibernate.shop.cart.CartRepository;
import hibernate.shop.product.Product;
import hibernate.shop.product.ProductRepository;
import hibernate.shop.user.User;
import hibernate.shop.user.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

public class AddProductToCartServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        User user= null;
        Optional<Cookie> emailCookie = Arrays.stream(req.getCookies()).filter(x -> x.getName().equals("email")).findFirst();
        if(emailCookie.isPresent()){
            Optional<User> byEmail = UserRepository.findByEmail(emailCookie.get().getValue());
            user=byEmail.orElse(null);
        }

        Long productId = parseStringToLong(req.getParameter("productId"));
        BigDecimal productAmount = parseStringToBigDecimal(req.getParameter("productAmount"));

        if (productId > 0 && productAmount.compareTo(BigDecimal.ZERO) == 1 && user!=null) {


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
                Optional<User> byEmail = UserRepository.findByEmail("asda@gg");
                Cart cart = new Cart();
                cart.setUser(byEmail.get());
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
        }
    }

    private Long parseStringToLong(String productId) {
        try {
            return Long.valueOf(productId);
        } catch (NumberFormatException nbf) {
            return 0L;
        }
    }

    private BigDecimal parseStringToBigDecimal(String productAmount) {
        try {
            return new BigDecimal(productAmount);
        } catch (NumberFormatException nbf) {
            return BigDecimal.ZERO;
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
