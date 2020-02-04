package by.epam.training.external.service;

import by.epam.training.external.dto.FlightDto;
import by.epam.training.external.entity.Crew;
import by.epam.training.external.entity.Employee;
import by.epam.training.external.entity.Flight;
import by.epam.training.external.service.util.HibernateSessionFactoryUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Locale;

public class DispatcherService {
    private static final Logger log = LogManager.getLogger(DispatcherService.class);
    private SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    private CrewService crewService = new CrewService();
    private FlightService flightService = new FlightService();
    private EmployeeService employeeService = new EmployeeService();

    public void disbandCrew(FlightDto flightDto, Locale locale) {
        Flight bobtailFlight = flightService.convertToFlight(flightDto, locale);
        bobtailFlight.setCrew(null);
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            flightService.updateFlight(bobtailFlight);
            Crew crew = crewService.findCrew(bobtailFlight.getCrew().getId());
            crewService.deleteCrew(crew);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            log.error(e);
        }
    }

    public Crew getCrew(int crewId) {
        if (crewId == 0) {
            return new Crew();
        }
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Crew crew = crewService.findCrew(crewId);
        tx.commit();
        return crew;
    }

    public List<Employee> loadAllUnusedEmployees(Crew crew) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<Employee> employees = employeeService.findAllEmployees();
        tx.commit();
        if (crew != null) {
            employees.removeAll(crew.getEmployees());
        }
        return employees;
    }

    public void fireEmployee(int employeeId) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.getTransaction();
        try {
            final Employee employee = employeeService.findEmployee(employeeId);
            employee.getCrews().forEach(crew -> {
                crew.getEmployees().remove(employee);
                crewService.updateCrew(crew);
            });
            employeeService.deleteEmployee(employee);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            log.error(e);
        }
    }

    public void editCrew(Flight flight) {
        Crew crew = flight.getCrew();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            if (crew.getId() == 0) {
                Flight flightWithFullData = flightService.findFlight(flight.getId());
                crewService.saveCrew(crew);
                flightWithFullData.setCrew(crew);
                flightService.updateFlight(flightWithFullData);
            }
            crewService.updateCrew(crew);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
        }
    }
}
