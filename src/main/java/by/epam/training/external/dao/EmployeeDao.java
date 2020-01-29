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
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(employee);
        tx.commit();
        session.close();
    }

    @Override
    public Employee findById(int id) {
        Session session = sessionFactory.openSession();
        Employee employee = session.get(Employee.class, id);
        session.close();
        return employee;
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Employee> findAll() {
        Session session = sessionFactory.openSession();
        Query<Employee> query = session.createQuery("FROM Employee");
        List<Employee> employees = query.list();
        session.close();
        return employees;
    }

    @Override
    public void update(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(employee);
        tx.commit();
        session.close();
    }

    @Override
    public void delete(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(employee);
        tx.commit();
        session.close();
    }
}