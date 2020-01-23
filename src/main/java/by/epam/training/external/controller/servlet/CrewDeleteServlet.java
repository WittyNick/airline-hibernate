package by.epam.training.external.controller.servlet;

import by.epam.training.external.entity.Crew;
import by.epam.training.external.entity.Flight;
import by.epam.training.external.entity.dto.FlightDto;
import by.epam.training.external.service.CrewService;
import by.epam.training.external.service.FlightService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Locale;

/**
 * Servlet mapping: "/crew/delete"
 */
@WebServlet("/crew/delete")
public class CrewDeleteServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger(CrewDeleteServlet.class);
    private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private CrewService crewService = new CrewService();
    private FlightService flightService = new FlightService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        BufferedReader reader = req.getReader();
        String jsonFlight;
        if (reader != null) {
            jsonFlight = reader.readLine();
        } else {
            resp.getWriter().print("fail");
            return;
        }
        // field Crew crew - contains only crewId
        FlightDto bobtailFlightDto = gson.fromJson(jsonFlight, FlightDto.class);
        Flight bobtailFlight = flightService.convertToFlight(bobtailFlightDto, findSessionLocale(req));

        Crew crew = crewService.findCrew(bobtailFlight.getCrew().getId());

        bobtailFlight.setCrew(null);
        flightService.updateFlight(bobtailFlight);

        crewService.deleteCrew(crew);
        resp.getWriter().print("ok");
    }

    private Locale findSessionLocale(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return (Locale) session.getAttribute("locale");
    }
}
