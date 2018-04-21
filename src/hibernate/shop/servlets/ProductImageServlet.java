package hibernate.shop.servlets;

import hibernate.shop.product.Product;
import hibernate.shop.product.ProductRepository;
import hibernate.shop.utils.ProjectHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "ProductImageServlet", urlPatterns = "/productImage")
public class ProductImageServlet extends HttpServlet {

/*    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long productId = ProjectHelper.parseStringToLong(request.getParameter("productId"));
        Optional<Product> oneById = ProductRepository.findOneById(productId);
        if(oneById.isPresent()){
            response.setHeader("Content-Length", String.valueOf(oneById.get().getImage().length));
            response.setHeader("Content-Disposition", "inline; filename=\""+oneById.get().getName()+"\"");
            //write image data to response
            response.getOutputStream().write(oneById.get().getImage());
        }
    }*/
}
