package hibernate.shop.servlets;

import hibernate.shop.cart.CartRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddProductToCartServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = 1L;
        String productId = req.getParameter("productId");
        String productAmount = req.getParameter("productAmount");

        CartRepository.findByUserId(id);
    }
}
