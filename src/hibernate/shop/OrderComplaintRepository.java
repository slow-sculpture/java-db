package hibernate.shop;

import hibernate.hibernate.util.HibernateUtil;
import org.hibernate.Session;

public class OrderComplaintRepository {

    public static void saveOrderComplaint(OrderComplaint orderComplaint) {

        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.saveOrUpdate(orderComplaint);
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
