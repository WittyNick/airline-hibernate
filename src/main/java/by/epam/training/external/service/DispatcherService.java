package by.epam.training.external.service;

import by.epam.training.external.dto.FlightDto;
import by.epam.training.external.entity.Crew;
import by.epam.training.external.entity.Employee;
import by.epam.training.external.entity.Flight;

import java.util.List;
import java.util.Locale;

public class DispatcherService {
    private CrewService crewService = new CrewService();
    private FlightService flightService = new FlightService();
    private EmployeeService employeeService = new EmployeeService();

    public void disbandCrew(FlightDto flightDto, Locale locale) {
        Flight bobtailFlight = flightService.convertToFlight(flightDto, locale);
        Crew crew = crewService.findCrew(bobtailFlight.getCrew().getId());
        bobtailFlight.setCrew(null);
        flightService.updateFlight(bobtailFlight);
        crewService.deleteCrew(crew);
    }

    public Crew getCrew(int crewId) {
        if (crewId == 0) {
            return new Crew();
        }
        return crewService.findCrew(crewId);
    }

    public List<Employee> loadAllUnusedEmployees(Crew crew) {
        List<Employee> employees = employeeService.findAllEmployees();
        if (crew != null) {
            employees.removeAll(crew.getEmployees());
        }
        return employees;
    }

    public void fireEmployee(int employeeId) {
        final Employee employee = employeeService.findEmployee(employeeId);
        employee.getCrews().forEach(crew -> {
            crew.getEmployees().remove(employee);
            crewService.updateCrew(crew);
        });
        employeeService.deleteEmployee(employee);
    }

    public void editCrew(Flight flight) {
        Crew crew = flight.getCrew();
        if (crew.getId() == 0) {
            Flight flightWithFullData = flightService.findFlight(flight.getId());
            crewService.saveCrew(crew);
            flightWithFullData.setCrew(crew);
            flightService.updateFlight(flightWithFullData);
        }
        crewService.updateCrew(crew);
    }
}
