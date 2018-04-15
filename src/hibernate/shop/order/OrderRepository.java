package hibernate.shop.order;

import hibernate.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OrderRepository {

    public static Optional<Order> findOrder(Long id){
        Session session=null;
        try{
            session=HibernateUtil.openSession();
            Optional<Order> order = Optional.ofNullable(session.find(Order.class, id));
            if(order.isPresent()){
                order.get().getOrderDetailSet();
            }
            return order;
        }catch (Exception ex){
            ex.printStackTrace();
            return Optional.empty();
        } finally {
            if(session!=null&&session.isOpen()){
                session.close();
            }
        }
    }

    public static void saveOrder(Order order) {

        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.saveOrUpdate(order);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }


    }

    public static List<Order> findAllDTO() {

        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String jpql = "SELECT new hibernate.shop.order.order(p.totalGross, p.userEmail) FROM order p";
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

    public static List<Order> findAllOrderWithProduct(Long productId) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String jpql = "SELECT o FROM order o LEFT JOIN FETCH o.orderDetailSet od WHERE od.product.id = :id";
            //String jpql2 = "SELECT o FROM OrderDetail od LEFT JOIN od.order o WHERE od.product.id = :id";
            Query query = session.createQuery(jpql);
            query.setParameter("id", productId);
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

    public static List<Order> findAll() {

        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String jpql = "SELECT o FROM order o LEFT JOIN FETCH o.orderDetailSet od";
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

    public static List<Order> findAllById(Long id, int offset) {

        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String jpql = "SELECT o FROM order o WHERE o.user.id = :id ORDER BY o.id DESC ";
            Query query = session.createQuery(jpql);
            query.setParameter("id", id);
            query.setMaxResults(15);
            query.setFirstResult(offset);
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
