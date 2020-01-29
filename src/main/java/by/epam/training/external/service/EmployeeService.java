package by.epam.training.external.service;

import by.epam.training.external.dao.DaoFactory;
import by.epam.training.external.dao.EmployeeDao;
import by.epam.training.external.entity.Employee;

import java.util.List;

public class EmployeeService {
    private EmployeeDao employeeDao = DaoFactory.getInstance().getEmployeeDao();

    public void saveEmployee(Employee employee) {
        employeeDao.save(employee);
    }

    public Employee findEmployee(int id) {
        return employeeDao.findById(id);
    }

    public List<Employee> findAllEmployees() {
        return employeeDao.findAll();
    }

    public void updateEmployee(Employee employee) {
        employeeDao.update(employee);
    }

    public void deleteEmployee(Employee employee) {
        employeeDao.delete(employee);
    }
}
