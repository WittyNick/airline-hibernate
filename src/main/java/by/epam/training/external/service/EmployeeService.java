package by.epam.training.external.service;

import by.epam.training.external.dao.DaoFactory;
import by.epam.training.external.dao.EmployeeDao;
import by.epam.training.external.entity.Employee;
import by.epam.training.external.service.util.HibernateSessionFactoryUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeService {
    private static final Logger log = LogManager.getLogger(EmployeeService.class);
    private SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    private EmployeeDao employeeDao = DaoFactory.getInstance().getEmployeeDao();

    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        employeeDao.save(employee);
        tx.commit();
    }

    public Employee findEmployee(int id) { // no tx fireEmployee()
        return employeeDao.findById(id);
    }

    public List<Employee> findAllEmployees() {
        return employeeDao.findAll();
    }

    public void deleteEmployee(Employee employee) { // no tx fireEmployee()
        employeeDao.delete(employee);
    }
}
