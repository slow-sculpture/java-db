package hibernate.shop;

import hibernate.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProductRepository {

    /**
     * Saves product to DB
     *
     * @param product
     */
    public static void saveProduct(Product product) {

        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.saveOrUpdate(product);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            if(session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }


    }

    /**
     * Removes product from DB by given id
     * @param id
     */
    public static void delete(Long id) {

        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.getTransaction().begin();
            Optional<Product> oneById = findOneById(id);
            if(oneById.isPresent()){
                session.delete(oneById.get());
            }
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            if(session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }


    }

    /**
     * Returns product from DB by id
     *
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
     *
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

    /**
     * Returns a list of all products by given product types
     *
     * @param productType
     * @return List<Product>
     */
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

    /**
     * Returns an amount of products by given product type
     *
     * @param productType
     * @return Long amountOfProducts
     */
    public static Long countByProductType(ProductType productType) {

        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String jpql = "SELECT count(p.id) FROM Product p WHERE p.productType= :productType";
            Query query = session.createQuery(jpql);
            query.setParameter("productType", productType);
            return (Long) query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }


    }

    /**
     * Returns a list of products with price less then given
     * @param priceGross
     * @return List<Product>
     */
    public static List<Product> findAllWithPriceLess(BigDecimal priceGross) {

        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String jpql = "SELECT p FROM Product p WHERE p.price.grossPrice < :price" ;
            Query query = session.createQuery(jpql);
            query.setParameter("price", priceGross);
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

    /**
     * Returns a list of all products by given name (upper/lower case or part of a name ->  doesn't matter)
     * @param name
     * @return List<Product>
     */
    public static List<Product> findAllByNameLike(String name) {

        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String jpql = "SELECT p FROM Product p WHERE UPPER(p.name) like :name";
            Query query = session.createQuery(jpql);
            query.setParameter("name","%"+ name.toUpperCase()+"%");
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
