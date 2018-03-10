package hibernate.shop;

import hibernate.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Optional;

public class ProductRepository {

    /**
     * Saves product to DB
     * @param product
     */
    public static void saveProduct(Product product) {

        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.save(product);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }


    }
    /**
     * Returns product from DB by id
     * @param id
     */
    public static Optional <Product> findOneById(Long id){
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            Product product = session.find(Product.class, id);
            return Optional.ofNullable(product);

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
