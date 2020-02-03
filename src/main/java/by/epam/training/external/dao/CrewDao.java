package by.epam.training.external.dao;

import by.epam.training.external.dao.util.HibernateSessionFactoryUtil;
import by.epam.training.external.entity.Crew;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CrewDao implements GenericDao<Crew> {
    private SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    CrewDao() {
    }

    @Override
    public void save(Crew crew) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(crew);
            tx.commit();
        }
    }

    @Override
    public Crew findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Crew.class, id);
        }
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Crew> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Crew> query = session.createQuery("FROM Crew");
            return query.list();
        }
    }

    @Override
    public void update(Crew crew) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(crew);
            tx.commit();
        }
    }

    @Override
    public void delete(Crew crew) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(crew);
            tx.commit();
        }
    }
}
