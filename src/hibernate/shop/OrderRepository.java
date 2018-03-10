package hibernate.shop;

import hibernate.hibernate.util.HibernateUtil;
import org.hibernate.Session;

public class OrderRepository {

    public static void saveOrder(Order order) {

        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.saveOrUpdate(order);
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
}
