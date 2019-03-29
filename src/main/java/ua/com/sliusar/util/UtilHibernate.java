package ua.com.sliusar.util;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Class UtilHibernate
 *
 * @author create by ivanslusar
 * 3/25/19
 * @project MyLuxoftProject
 */
public class UtilHibernate {

    private static final SessionFactory sessionFactory;
    private static final Logger LOGGER = Logger.getLogger(UtilHibernate.class);

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            LOGGER.error("Initial SessionFactory creation failed.",ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }

}
