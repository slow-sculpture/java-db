package hibernate.shop.product;

import hibernate.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
     * @return product or null
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
     * @return List<product>
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
     * @return List<product>
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
     * @return List<product>
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
     * @return List<product>
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

    /**
     * Returns a list of all products by given name (upper/lower case or part of a name ->  doesn't matter)
     * using given criterias
     * Method is equal above method findAllByNamesLike
     * @param
     * @return List<product>
     */
    public static List<Product> findAllByNameLikeWithCriteria(String name){
        Session session = null;
        try {
            //otwieramy sesje
            session = HibernateUtil.openSession();
            //tworzym CriteriaBuilder
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = cb.createQuery(Product.class);
            //definiujemy z jakiej encji pobieramy -> FROM
            Root<Product> from = query.from(Product.class);
            query.select(from);
            //predykaty, czyli warunki WHERE
            Predicate whereNameLike = cb.like(
                    from.get("name"), "%" + name.toUpperCase() + "%");
            CriteriaQuery<Product> criteriaQuery = query.where(whereNameLike);

            return session.createQuery(criteriaQuery).getResultList();

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
     * Return all products using MySQL language
     * @return List<product>
     */
    public static List<Product> findAllNative() {

        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String sql = "SELECT * FROM product";
            Query query = session.createNativeQuery(sql, Product.class);
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
     * Returns all cars or toys with price less then given
     * @param priceGross
     * @return
     */
    public static List<Product> findAllCarOrToyWithPriceLessThen(BigDecimal priceGross) {

        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String jpql = "SELECT p FROM Product p WHERE (p.productType = :toy OR " +
                    "p.productType = :car) AND p.price.grossPrice < :price";
            Query query = session.createQuery(jpql);
            query.setParameter("price", priceGross);
            query.setParameter("toy", ProductType.TOY);
            query.setParameter("car", ProductType.CAR);
            query.setMaxResults(30);
            query.setFirstResult(0);
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

    //===================== same using criteria builder ==========================\\
    public static List<Product> findAllCarOrToyWithPriceLessThenWithCriteria(BigDecimal priceGrosse){
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = cb.createQuery(Product.class);
            Root<Product> from = query.from(Product.class);
            query.select(from);
            Predicate toyPredicate = cb.equal(from.get("productType"), ProductType.TOY);
            Predicate carPredicate = cb.equal(from.get("productType"), ProductType.CAR);
            Predicate pricePredicate = cb.lessThan(from.get("price.grossPrice"), priceGrosse);
            Predicate toyOrCar = cb.or(toyPredicate, carPredicate);
            Predicate whereToyOrCarAndPrice = cb.and(toyOrCar, pricePredicate);
            CriteriaQuery<Product> criteriaQuery = query.where(whereToyOrCarAndPrice);

            return session.createQuery(criteriaQuery).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static  List<ProductRating> findAllByProductId(Long id){
        Session session = null;
        try{
            session=HibernateUtil.openSession();
            String jpql = "SELECT p FROM ProductRating p WHERE product.id=:productRating";
            Query query = session.createQuery(jpql);
            query.setParameter("productRating", id);
            return query.getResultList();
        }catch (Exception ex){
            ex.printStackTrace();
            return Collections.emptyList();
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public static  Double findAllByProductIdAvg(Long id){
        Session session = null;
        try{
            session=HibernateUtil.openSession();
            String jpql = "SELECT avg(p.rating) FROM ProductRating p WHERE product.id=:productRating";
            Query query = session.createQuery(jpql);
            query.setParameter("productRating", id);
            return (Double)query.getSingleResult();
        }catch (Exception ex){
            ex.printStackTrace();
            return 0d;
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }
}
