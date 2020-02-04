package by.epam.training.external.dao;

import by.epam.training.external.entity.Crew;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CrewDao extends GenericDao<Crew> {

    CrewDao() {
    }

    @Override
    public void save(Crew crew) {
        Session session = sessionFactory.getCurrentSession();
        session.save(crew);
    }

    @Override
    public Crew findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Crew.class, id);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Crew> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Crew> query = session.createQuery("FROM Crew");
        return query.list();
    }

    @Override
    public void update(Crew crew) {
        Session session = sessionFactory.getCurrentSession();
        session.update(crew);
    }

    @Override
    public void delete(Crew crew) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(crew);
    }
}
