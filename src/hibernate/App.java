package hibernate;


import hibernate.shop.Price;
import hibernate.shop.Product;
import hibernate.shop.ProductRepository;
import hibernate.shop.ProductType;



import java.math.BigDecimal;


public class App {

    public static void main(String[] args) throws Exception {
        Product merc = new Product("Mercedes S", ProductType.CAR,
                new Price(new BigDecimal(40000), new BigDecimal(600000)));
        ProductRepository.saveProduct(merc);

    }
}
