package by.epam.training.external.dao.hibernate;

import by.epam.training.external.dao.CrewDao;
import by.epam.training.external.dao.hibernate.util.HibernateSessionFactoryUtil;
import by.epam.training.external.entity.Crew;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CrewDaoHibernate implements CrewDao {
    private SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    @Override
    public void save(Crew crew) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(crew);
        tx.commit();
        session.close();
    }

    @Override
    public Crew findById(int id) {
        Session session = sessionFactory.openSession();
        Crew crew = session.get(Crew.class, id);
        session.close();
        return crew;
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Crew> findAll() {
        Session session = sessionFactory.openSession();
        Query<Crew> query = session.createQuery("FROM Crew");
        List<Crew> crews = query.list();
        session.close();
        return crews;
    }

    @Override
    public void update(Crew crew) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(crew);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Crew crew) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(crew);
        tx.commit();
        session.close();
    }
}
