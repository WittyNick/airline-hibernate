package by.epam.training.external.service;

import by.epam.training.external.dto.FlightDto;
import by.epam.training.external.entity.Crew;
import by.epam.training.external.entity.Flight;
import by.epam.training.external.service.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AdministratorService {
    private SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    private FlightService flightService = new FlightService();
    private final String dateHtmlInputPattern = "yyyy-MM-dd";
    private final String htmlHtmlInputPattern = "HH:mm";

    public void cancelFlight(FlightDto flightDto) {
        Flight bobtailFlight = flightService.convertToFlight(flightDto, "");
        flightService.deleteFlight(bobtailFlight);
    }

    public FlightDto getFlightDtoById(int flightId) {
        if (flightId == 0) {
            FlightDto flightDto = new FlightDto();
            flightDto.setCrew(new Crew());
            return flightDto; // flightId = 0, crewId = 0;
        }
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        Flight flight = flightService.findFlight(flightId);
        tx.commit();
        return flightService.convertToDto(flight, dateHtmlInputPattern, htmlHtmlInputPattern);
    }

    public void createOrUpdateFlight(FlightDto flightDto) {
        String dateTimePattern = dateHtmlInputPattern + " " + htmlHtmlInputPattern;
        Flight bobtailFlight = flightService.convertToFlight(flightDto, dateTimePattern);
        Crew crew = bobtailFlight.getCrew();
        if (crew != null && crew.getId() == 0) {
            bobtailFlight.setCrew(null);
        }
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            if (flightDto.getId() > 0) {
                flightService.updateFlight(bobtailFlight);
            } else {
                flightService.saveFlight(bobtailFlight);
            }
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
        }
    }
}
