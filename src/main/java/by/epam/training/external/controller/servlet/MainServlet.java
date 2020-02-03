package by.epam.training.external.controller.servlet;

import by.epam.training.external.dto.FlightDto;
import by.epam.training.external.service.FlightService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private FlightService flightService = new FlightService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Locale locale = findSessionLocale(req);
        List<FlightDto> flightsDto = flightService.getAllFlightsDto(locale);
        req.setAttribute("flights", flightsDto);
        req.getRequestDispatcher("view/main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Locale locale = findSessionLocale(req);
        List<FlightDto> flightsDto = flightService.getAllFlightsDto(locale);
        String json = gson.toJson(flightsDto);
        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().write(json);
    }

    private Locale findSessionLocale(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Locale locale = (Locale) session.getAttribute("locale");
        if (locale != null) {
            return locale;
        }
        return req.getLocale();
    }
}
