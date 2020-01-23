package by.epam.training.external.dao;

import by.epam.training.external.dao.hibernate.DaoFactoryHibernate;

public abstract class DaoFactory {
    public abstract CrewDao getCrewDao();
    public abstract FlightDao getFlightDao();
    public abstract EmployeeDao getEmployeeDao();

    public static DaoFactory getDaoFactory() {
        return DaoFactoryHibernate.getDaoFactory();
    }
}
