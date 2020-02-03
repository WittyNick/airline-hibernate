package by.epam.training.external.dao;

import by.epam.training.external.dao.util.HibernateSessionFactoryUtil;
import by.epam.training.external.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDao implements GenericDao<Employee> {
    private SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    EmployeeDao() {
    }

    @Override
    public void save(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(employee);
            tx.commit();
        }
    }

    @Override
    public Employee findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Employee.class, id);
        }
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Employee> findAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Employee> query = session.createQuery("FROM Employee");
            return query.list();
        }
    }

    @Override
    public void update(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(employee);
            tx.commit();
        }
    }

    @Override
    public void delete(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(employee);
            tx.commit();
        }
    }
}