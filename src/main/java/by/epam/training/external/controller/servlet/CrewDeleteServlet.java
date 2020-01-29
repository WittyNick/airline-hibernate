package by.epam.training.external.controller.servlet;

import by.epam.training.external.dto.FlightDto;
import by.epam.training.external.service.DispatcherService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private DispatcherService dispatcherService = new DispatcherService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        String jsonFlightDto = readJson(req);
        if (jsonFlightDto.isEmpty()) {
            resp.getWriter().print("fail");
            return;
        }
//        // field Crew crew - contains only crewId
        FlightDto bobtailFlightDto = gson.fromJson(jsonFlightDto, FlightDto.class);
        Locale locale = findSessionLocale(req);
        dispatcherService.disbandCrew(bobtailFlightDto, locale);
        resp.getWriter().print("ok");
    }

    private String readJson(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        if (reader == null) {
            return "";
        }
        return reader.readLine();
    }

    private Locale findSessionLocale(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return (Locale) session.getAttribute("locale");
    }
}
