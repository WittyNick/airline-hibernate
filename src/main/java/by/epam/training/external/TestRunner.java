package by.epam.training.external;

import by.epam.training.external.entity.Crew;
import by.epam.training.external.entity.Employee;
import by.epam.training.external.entity.Flight;
import by.epam.training.external.service.CrewService;
import by.epam.training.external.service.EmployeeService;
import by.epam.training.external.service.FlightService;

import java.time.LocalDateTime;

public class TestRunner {
    private static FlightService flightService = new FlightService();
    private static CrewService crewService = new CrewService();
    private static EmployeeService employeeService = new EmployeeService();

    public static void main(String[] args) {
        Crew crew1 = new Crew("Команда А");
        Crew crew2 = new Crew("Команда Б");

        Employee employee1 = new Employee("Иван", "Драго", Employee.Position.PILOT);
        Employee employee2 = new Employee("Юрий", "Бойка", Employee.Position.NAVIGATOR);
        Employee employee3 = new Employee("Татьяна", "Минеева", Employee.Position.STEWARDESS);
        employeeService.saveEmployee(employee1);
        employeeService.saveEmployee(employee2);
        employeeService.saveEmployee(employee3);

        crew1.getEmployees().add(employee1);
        crew1.getEmployees().add(employee2);
        crew2.getEmployees().add(employee3);

        Flight flight = new Flight(
                111, "Гомель", "Москва",
                LocalDateTime.of(2020, 1, 20, 13,20),
                LocalDateTime.of(2020, 1, 20, 16, 30),
                "SuperJet"
        );

        flight.setCrew(crew1);

        flightService.saveFlight(flight);
    }
}