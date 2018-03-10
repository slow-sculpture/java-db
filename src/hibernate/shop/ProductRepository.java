package hibernate.shop;

import hibernate.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.util.Collections;
import java.util.List;
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
     * @return Product or null
     */
    public static Optional<Product> findOneById(Long id) {
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

    /**
     * Returns a list of all products
     * @return List<Product>
     */
    public static List<Product> findAll() {

        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String jpql = "SELECT p FROM Product p";
            Query query = session.createQuery(jpql);
            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }


    }

    public static List<Product> findAllByProductType(ProductType productType) {

        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String jpql = "SELECT p FROM Product p WHERE p.productType= :productType";
            Query query = session.createQuery(jpql);
            query.setParameter("productType", productType);
            return query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }


    }
}
