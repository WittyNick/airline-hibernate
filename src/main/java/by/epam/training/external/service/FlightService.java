package by.epam.training.external.service;

import by.epam.training.external.dao.DaoFactory;
import by.epam.training.external.dao.FlightDao;
import by.epam.training.external.dto.FlightDto;
import by.epam.training.external.entity.Crew;
import by.epam.training.external.entity.Flight;
import by.epam.training.external.locale.LocaleManager;
import by.epam.training.external.service.util.HibernateSessionFactoryUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FlightService {
    private static final Logger log = LogManager.getLogger(FlightService.class);
    private LocaleManager manager = LocaleManager.INSTANCE;
    private SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    private FlightDao flightDao = DaoFactory.getInstance().getFlightDao();

    public void saveFlight(Flight flight) {
        flightDao.save(flight);
    }

    public Flight findFlight(int id) {
        return flightDao.findById(id);
    }

    public List<Flight> findAllFlights() {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        List<Flight> flights = flightDao.findAll();
        tx.commit();
        return flights;

    }

    public void updateFlight(Flight flight) {
        flightDao.update(flight);
    }

    public void deleteFlight(Flight flight) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        try {
            flightDao.delete(flight);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            log.error(e);
        }
    }

    public List<FlightDto> getAllFlightsDto(Locale locale) {
        List<Flight> flights = findAllFlights();
        return convertToDtoList(flights, locale);
    }

    private List<FlightDto> convertToDtoList(List<Flight> flights, Locale locale) {
        final List<FlightDto> flightsDto = new ArrayList<>();
        flights.forEach(flight -> flightsDto.add(convertToDto(flight, locale)));
        return flightsDto;
    }

    private FlightDto convertToDto(Flight flight, Locale locale) {
        String datePattern = getLocaleDatePattern(locale);
        String timePattern = getLocaleTimePattern(locale);
        return convertToDto(flight, datePattern, timePattern);
    }

    public FlightDto convertToDto(Flight flight, String datePattern, String timePattern) {
        FlightDto flightDto = new FlightDto();
        flightDto.setId(flight.getId());
        flightDto.setFlightNumber(flight.getFlightNumber());
        flightDto.setStartPoint(flight.getStartPoint());
        flightDto.setDestinationPoint(flight.getDestinationPoint());
        flightDto.setDepartureDate(convertLocalDateTimeToString(flight.getDepartureDateTime(), datePattern));
        flightDto.setDepartureTime(convertLocalDateTimeToString(flight.getDepartureDateTime(), timePattern));
        flightDto.setArrivalDate(convertLocalDateTimeToString(flight.getArrivalDateTime(), datePattern));
        flightDto.setArrivalTime(convertLocalDateTimeToString(flight.getArrivalDateTime(), timePattern));
        flightDto.setPlane(flight.getPlane());
        flightDto.setCrew(flight.getCrew());
        return flightDto;
    }

    public String convertLocalDateTimeToString(LocalDateTime dateTime, String pattern) {
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    private String getLocaleDatePattern(Locale locale) {
        return manager.getString(locale, "date.format");
    }

    private String getLocaleTimePattern(Locale locale) {
        return manager.getString(locale, "time.format");
    }

    public Flight convertToFlight(FlightDto flightDto, Locale locale) {
        String dateTimePattern = getLocaleDatePattern(locale) + " " + getLocaleTimePattern(locale);
        return convertToFlight(flightDto, dateTimePattern);
    }

    public Flight convertToFlight(FlightDto flightDto, String dateTimePattern) {
        Flight flight = new Flight();
        flight.setId(flightDto.getId());
        flight.setFlightNumber(flightDto.getFlightNumber());
        flight.setStartPoint(flightDto.getStartPoint());
        flight.setDestinationPoint(flightDto.getDestinationPoint());
        if (!dateTimePattern.isEmpty()) {
            flight.setDepartureDateTime(
                    convertToLocalDateTime(flightDto.getDepartureDate(), flightDto.getDepartureTime(), dateTimePattern)
            );
            flight.setArrivalDateTime(
                    convertToLocalDateTime(flightDto.getArrivalDate(), flightDto.getArrivalTime(), dateTimePattern)
            );
        }
        flight.setPlane(flightDto.getPlane());

        Crew crew = flightDto.getCrew();
        if (crew.getId() > 0) {
            flight.setCrew(crew);
        }
        return flight;
    }

    private LocalDateTime convertToLocalDateTime(String date, String time, String dateTimePattern) {
        String dateTime = date + " " + time;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimePattern);
        return LocalDateTime.parse(dateTime, formatter);
    }
}
