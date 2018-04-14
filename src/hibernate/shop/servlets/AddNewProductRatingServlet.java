package hibernate.shop.servlets;

import hibernate.shop.product.Product;
import hibernate.shop.product.ProductRating;
import hibernate.shop.product.ProductRatingRepository;
import hibernate.shop.product.ProductRepository;
import hibernate.shop.user.User;
import hibernate.shop.utils.ProjectHelper;
import hibernate.shop.utils.UserSessionHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet(name = "AddNewProductRatingServlet", urlPatterns = "/addNewProductRating")
public class AddNewProductRatingServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User userFromCookie = UserSessionHelper.getUserFromCookie(request.getCookies());
        String description = request.getParameter("description");
        Optional<Product> product = ProductRepository.findOneById(ProjectHelper.parseStringToLong(request.getParameter("productId")));
        double rating = ProjectHelper.parseStringToBigDecimal(request.getParameter("rating")).doubleValue();

        if(product.isPresent()&&userFromCookie!=null&&rating>0) {
            ProductRating productRating = new ProductRating();
            productRating.setCreateDate(LocalDateTime.now());
            productRating.setDescription(description);
            productRating.setRating(rating);
            productRating.setUser(userFromCookie);
            productRating.setProduct(product.get());
            ProductRatingRepository.saveProductRating(productRating);
        }
    }


}
