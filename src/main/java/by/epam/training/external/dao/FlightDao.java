package by.epam.training.external.dao;

import by.epam.training.external.entity.Flight;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class FlightDao extends GenericDao<Flight> {

    FlightDao() {
    }

    @Override
    public void save(Flight flight) {
        Session session = sessionFactory.getCurrentSession();
        session.save(flight);
    }

    @Override
    public Flight findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Flight.class, id);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Flight> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Flight> query = session.createQuery("FROM Flight");
        return query.list();
    }

    @Override
    public void update(Flight flight) {
        Session session = sessionFactory.getCurrentSession();
        session.update(flight);
    }

    @Override
    public void delete(Flight flight) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(flight);
    }
}
