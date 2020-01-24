package by.epam.training.external.service;

import by.epam.training.external.entity.Crew;
import by.epam.training.external.entity.Employee;
import by.epam.training.external.entity.Flight;
import by.epam.training.external.entity.dto.FlightDto;

import java.util.*;

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

    public Map<String, String> getParameterMap(int flightId, int crewId) {
        Map<String, String> map = new HashMap<>();
        map.put("#flight.id", "" + flightId);
        map.put("#crew.id", "" + crewId);
        List<Employee> employeeBase = employeeService.findAllEmployees();
        if (crewId > 0) {
            Crew crew = crewService.findCrew(crewId);
            Set<Employee> crewEmployees = crew.getEmployees();
            map.put("#crew.name", crew.getName());
            map.put("#employee.list.body", createTbodyEmployee(crewEmployees));
            employeeBase.removeAll(crewEmployees);
        } else {
            map.put("#crew.name", "");
            map.put("#employee.list.body", "");
        }
        map.put("#employee.base.body", createTbodyEmployee(employeeBase));
        return map;
    }

    private String createTbodyEmployee(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (Employee employee : employees) {
            result.append(createTrEmployee(employee));
        }
        return result.toString();
    }

    private String createTbodyEmployee(Set<Employee> employees) {
        return createTbodyEmployee(new ArrayList<>(employees));
    }

    private String createTrEmployee(Employee employee) {
        return "<tr>" +
                "<td>" + employee.getId() + "</td>" +
                "<td>" + employee.getName() + "</td>" +
                "<td>" + employee.getSurname() + "</td>" +
                "<td>" + employee.getPosition().name() + "</td>" +
                "<td></td>" +
                "</tr>";
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
