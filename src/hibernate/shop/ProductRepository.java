package hibernate.shop;

import hibernate.hibernate.util.HibernateUtil;
import org.hibernate.Session;

public class ProductRepository {

    public static void saveProduct(Product product){


        try {
            Session session = HibernateUtil.openSession();
            session.save(product);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
