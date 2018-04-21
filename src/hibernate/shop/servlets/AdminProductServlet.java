package hibernate.shop.servlets;

import hibernate.shop.product.Price;
import hibernate.shop.product.Product;
import hibernate.shop.product.ProductRepository;
import hibernate.shop.product.ProductType;
import hibernate.shop.utils.ProjectHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@WebServlet(name = "AdminProductServlet")
public class AdminProductServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String productType = request.getParameter("productType");
        BigDecimal nettoPrice = ProjectHelper.parseStringToBigDecimal(request
        .getParameter("nettoPrice"));
        BigDecimal grossPrice = ProjectHelper.parseStringToBigDecimal(request
                .getParameter("grossPrice"));

        Product product = new Product();
        product.setDate(LocalDate.now());
        product.setDescription(description);
        product.setName(name);
        Price price=new Price();
        price.setGrossPrice(grossPrice);
        price.setNettoPrice(nettoPrice);
        product.setPrice(price);
        product.setProductType(ProductType.valueOf(productType));
        ProductRepository.saveProduct(product);

    }
}
