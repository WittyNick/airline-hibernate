package by.epam.training.external.service.util;

import by.epam.training.external.entity.Crew;
import by.epam.training.external.entity.Employee;
import by.epam.training.external.entity.Flight;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration config = new Configuration().configure();
            config.addAnnotatedClass(Flight.class);
            config.addAnnotatedClass(Crew.class);
            config.addAnnotatedClass(Employee.class);

            StandardServiceRegistryBuilder serviceRegistryBuilder =
                    new StandardServiceRegistryBuilder().applySettings(config.getProperties());
            sessionFactory = config.buildSessionFactory(serviceRegistryBuilder.build());
        }
        return sessionFactory;
    }
}
