package by.epam.training.external.dao;

import by.epam.training.external.dao.util.HibernateSessionFactoryUtil;
import by.epam.training.external.entity.Flight;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class FlightDao implements GenericDao<Flight> {
    private SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    FlightDao() {
    }

    @Override
    public void save(Flight flight) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(flight);
            tx.commit();
        }
    }

    @Override
    public Flight findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Flight.class, id);
        }
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Flight> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Flight> query = session.createQuery("FROM Flight");
            return query.list();
        }
    }

    @Override
    public void update(Flight flight) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(flight);
            tx.commit();
        }
    }

    @Override
    public void delete(Flight flight) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(flight);
            tx.commit();
        }
    }
}
