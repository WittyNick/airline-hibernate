package by.epam.training.external.dao;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public CrewDao getCrewDao() {
        return new CrewDao();
    }

    public FlightDao getFlightDao() {
        return new FlightDao();
    }

    public EmployeeDao getEmployeeDao() {
        return new EmployeeDao();
    }
}
