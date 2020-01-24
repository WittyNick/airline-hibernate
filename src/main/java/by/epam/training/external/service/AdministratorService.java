package by.epam.training.external.service;

import by.epam.training.external.entity.Crew;
import by.epam.training.external.entity.Flight;
import by.epam.training.external.entity.dto.FlightDto;

import java.util.HashMap;
import java.util.Map;

public class AdministratorService {
    private FlightService flightService = new FlightService();
    private final String dateHtmlInputPattern = "yyyy-MM-dd";
    private final String tmeHtmlInputPattern = "HH:mm";

    public void cancelFlight(FlightDto flightDto) {
        Flight bobtailFlight = flightService.convertToFlight(flightDto, "");
        flightService.deleteFlight(bobtailFlight);
    }

    public Map<String, String> getPageParameters(int flightId) {
        if (flightId == 0) {
            return getEmptyParameterMap();
        }
        Flight flight = flightService.findFlight(flightId);
        FlightDto flightDto = flightService.convertToDto(flight, dateHtmlInputPattern, tmeHtmlInputPattern);
        return fillParameterMap(flightDto);
    }

    private Map<String, String> fillParameterMap(FlightDto flightDto) {
        Map<String, String> map = new HashMap<>();
        map.put("#id", "" + flightDto.getId());
        map.put("#flight.number", "" + flightDto.getFlightNumber());
        map.put("#start.point", flightDto.getStartPoint());
        map.put("#destination.point", flightDto.getDestinationPoint());
        map.put("#departure.date", flightDto.getDepartureDate());
        map.put("#departure.time", flightDto.getDepartureTime());
        map.put("#arrival.date", flightDto.getArrivalDate());
        map.put("#arrival.time", flightDto.getArrivalTime());
        map.put("#plane", flightDto.getPlane());
        if (flightDto.getCrew() != null) {
            map.put("#crew.id", "" + flightDto.getCrew().getId());
        } else {
            map.put("#crew.id", "0");
        }
        return map;
    }

    private Map<String, String> getEmptyParameterMap() {
        Map<String, String> map = new HashMap<>();
        map.put("#id", "0");
        map.put("#flight.number", "");
        map.put("#start.point", "");
        map.put("#destination.point", "");
        map.put("#departure.date", "");
        map.put("#departure.time", "");
        map.put("#arrival.date", "");
        map.put("#arrival.time", "");
        map.put("#plane", "");
        map.put("#crew.id", "0");
        return map;
    }

    public void createOrUpdateFlight(FlightDto flightDto) {
        String dateTimePattern = dateHtmlInputPattern + " " + tmeHtmlInputPattern;
        Flight bobtailFlight = flightService.convertToFlight(flightDto, dateTimePattern);
        Crew crew = bobtailFlight.getCrew();
        if (crew != null && crew.getId() == 0) {
            bobtailFlight.setCrew(null);
        }
        if (flightDto.getId() > 0) {
            flightService.updateFlight(bobtailFlight);
        } else {
            flightService.saveFlight(bobtailFlight);
        }
    }
}
