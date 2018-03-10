package hibernate;

import hibernate.hibernate.util.HibernateUtil;
import hibernate.shop.Price;
import hibernate.shop.Product;
import hibernate.shop.ProductType;
import org.hibernate.Session;


import java.math.BigDecimal;


public class App {

    public static void main(String[] args) throws Exception {
        try {
            Session session = HibernateUtil.openSession();

            Product merc = new Product("Mercedes S", ProductType.CAR,
                    new Price(new BigDecimal(50000), new BigDecimal(600000)));

            session.save(merc);
            session.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
