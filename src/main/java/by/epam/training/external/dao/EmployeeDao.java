package by.epam.training.external.dao;

import by.epam.training.external.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDao extends GenericDao<Employee> {

    EmployeeDao() {
    }

    @Override
    public void save(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.save(employee);
    }

    @Override
    public Employee findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, id);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Employee> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Employee> query = session.createQuery("FROM Employee");
        return query.list();
    }

    @Override
    public void update(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.update(employee);
    }

    @Override
    public void delete(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(employee);
    }
}