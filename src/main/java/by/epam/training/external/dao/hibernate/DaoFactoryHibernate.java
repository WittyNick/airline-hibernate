package by.epam.training.external.dao.hibernate;

import by.epam.training.external.dao.CrewDao;
import by.epam.training.external.dao.DaoFactory;
import by.epam.training.external.dao.EmployeeDao;
import by.epam.training.external.dao.FlightDao;

public class DaoFactoryHibernate extends DaoFactory {
    private static final DaoFactoryHibernate instance = new DaoFactoryHibernate();

    private DaoFactoryHibernate() {}

    public static DaoFactory getDaoFactory() {
        return instance;
    }

    @Override
    public CrewDao getCrewDao() {
        return new CrewDaoHibernate();
    }

    @Override
    public FlightDao getFlightDao() {
        return new FlightDaoHibernate();
    }

    @Override
    public EmployeeDao getEmployeeDao() {
        return new EmployeeDaoHibernate();
    }
}
