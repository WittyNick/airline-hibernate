package by.epam.training.external.dao.hibernate;

import by.epam.training.external.dao.FlightDao;
import by.epam.training.external.dao.hibernate.util.HibernateSessionFactoryUtil;
import by.epam.training.external.entity.Flight;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class FlightDaoHibernate implements FlightDao {
    private SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    @Override
    public void save(Flight flight) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(flight);
        tx.commit();
        session.close();
    }

    @Override
    public Flight findById(int id) {
        Session session = sessionFactory.openSession();
        Flight flight = session.get(Flight.class, id);
        session.close();
        return flight;
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Flight> findAll() {
        Session session = sessionFactory.openSession();
        Query<Flight> query = session.createQuery("FROM Flight");
        List<Flight> flights = query.list();
        session.close();
        return flights;
    }

    @Override
    public void update(Flight flight) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(flight);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Flight flight) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(flight);
        tx.commit();
        session.close();
    }
}
