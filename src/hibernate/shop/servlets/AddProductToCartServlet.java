package hibernate.shop.servlets;

import hibernate.shop.cart.Cart;
import hibernate.shop.cart.CartDetail;
import hibernate.shop.cart.CartRepository;
import hibernate.shop.product.Product;
import hibernate.shop.product.ProductRepository;
import hibernate.shop.user.User;
import hibernate.shop.user.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.OptionalInt;

public class AddProductToCartServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = 1L;
        String productId = req.getParameter("productId");
        String productAmount = req.getParameter("productAmount");

        Optional<Cart> byUserId = CartRepository.findByUserId(userId);
        if(byUserId.isPresent()){
            Cart cart = byUserId.get();
            Optional<CartDetail> productInCart=cart.getCartDetailSet().stream()
                    .filter(cd->cd.getProduct().getId().equals(Integer.valueOf(productId)))
                    .findFirst();
            if(productInCart.isPresent()){
                //produkt jest juz w koszyku
                productInCart.get().setAmount(productInCart.get().getAmount().add(new BigDecimal(productAmount)));
                CartRepository.saveCart(cart);
            }

        }else {
            Optional<User> byEmail= UserRepository.findByEmail("test@wp.pl");
            Cart cart = new Cart();
            cart.setUser(byEmail.get());
            cart.setCartDetailSet(new HashSet<>());
            CartDetail cartDetail = new CartDetail();
            cartDetail.setAmount(new BigDecimal(productAmount));
            Optional<Product> oneById = ProductRepository.findOneById(Long.valueOf(productId));
            cartDetail.setProduct(oneById.get());
            cartDetail.setPrice(oneById.get().getPrice());
            cart.addCartDetail(cartDetail);

            CartRepository.saveCart(cart);



        }
    }
}
