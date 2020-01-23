package by.epam.training.external.service;

import by.epam.training.external.config.ConfigurationManager;
import by.epam.training.external.dao.DaoFactory;
import by.epam.training.external.dao.FlightDao;
import by.epam.training.external.entity.Flight;
import by.epam.training.external.entity.dto.FlightDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FlightService {
    ConfigurationManager manager = ConfigurationManager.INSTANCE;

    private FlightDao flightDao = DaoFactory.getDaoFactory().getFlightDao();

    public void saveFlight(Flight flight) {
        flightDao.save(flight);
    }

    public Flight findFlight(int id) {
        return flightDao.findById(id);
    }

    public List<Flight> findAllFlights() {
        return flightDao.findAll();
    }

    public void updateFlight(Flight flight) {
        flightDao.update(flight);
    }

    public void deleteFlight(Flight flight) {
        flightDao.delete(flight);
    }

    public List<FlightDto> getAllFlightsDto(Locale locale) {
        List<Flight> flights = findAllFlights();
        return convertToDtoList(flights, locale);
    }

    private List<FlightDto> convertToDtoList(List<Flight> flights, Locale locale) {
        final List<FlightDto> flightsDto = new ArrayList<>();
        flights.forEach(flight -> {
            FlightDto flightDto = new FlightDto();
            flightDto.setId(flight.getId());
            flightDto.setFlightNumber(flight.getFlightNumber());
            flightDto.setStartPoint(flight.getStartPoint());
            flightDto.setDestinationPoint(flight.getDestinationPoint());
            flightDto.setDepartureDate(convertToStringDate(flight.getDepartureDateTime(), locale));
            flightDto.setDepartureTime(convertToStringTime(flight.getDepartureDateTime(), locale));
            flightDto.setArrivalDate(convertToStringDate(flight.getArrivalDateTime(), locale));
            flightDto.setArrivalTime(convertToStringTime(flight.getArrivalDateTime(), locale));
            flightDto.setPlane(flight.getPlane());
            flightDto.setCrew(flight.getCrew());
            flightsDto.add(flightDto);
        });
        return flightsDto;
    }

    private String convertToStringDate(LocalDateTime dateTime, Locale locale) {
        return dateTime.format(DateTimeFormatter.ofPattern(manager.getText(locale, "date.format")));
    }

    private String convertToStringTime(LocalDateTime dateTime, Locale locale) {
        return dateTime.format(DateTimeFormatter.ofPattern(manager.getText(locale, "time.format")));
    }

    public Flight convertToFlight(FlightDto flightDto, Locale locale) {
        Flight flight = new Flight();
        flight.setId(flightDto.getId());
        flight.setFlightNumber(flightDto.getFlightNumber());
        flight.setStartPoint(flightDto.getStartPoint());
        flight.setDestinationPoint(flightDto.getDestinationPoint());

//        flight.setDepartureDateTime(); add this
//        flight.setArrivalDateTime();

        flight.setPlane(flightDto.getPlane());
        flight.setCrew(flightDto.getCrew());
        return flight;
    }
}
