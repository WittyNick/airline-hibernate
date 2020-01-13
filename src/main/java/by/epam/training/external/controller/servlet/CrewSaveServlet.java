package by.epam.training.external.controller.servlet;

import by.epam.training.external.entity.Flight;
import by.epam.training.external.service.Service;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class CrewSaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Service service = Service.INSTANCE;
        resp.setContentType("text/plain; charset=UTF-8");
        BufferedReader reader = req.getReader();
        String jsonFlight = "";
        if (reader != null) {
            jsonFlight = reader.readLine();
        } else {
            resp.getWriter().print("fail");
        }
        Gson gson = new Gson();
        Flight bobtailFlight = gson.fromJson(jsonFlight, Flight.class);
        int crewId = bobtailFlight.getCrew().getId();
        if (crewId == 0) {
            service.create(bobtailFlight.getCrew());
            Flight flight = service.readFlightById(bobtailFlight.getId());
            flight.setCrew(bobtailFlight.getCrew());
            service.update(flight);
        }
        service.update(bobtailFlight.getCrew());
        resp.getWriter().print("ok");
    }
}
