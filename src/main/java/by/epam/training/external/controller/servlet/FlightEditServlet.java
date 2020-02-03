package by.epam.training.external.controller.servlet;

import by.epam.training.external.dto.FlightDto;
import by.epam.training.external.service.AdministratorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/administrator/edit")
public class FlightEditServlet extends HttpServlet {
    private AdministratorService adminService = new AdministratorService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int flightId = Integer.parseInt(req.getParameter("flightId"));
        FlightDto flightDto = adminService.getFlightDtoById(flightId);
        req.setAttribute("flight", flightDto);
        req.getRequestDispatcher("/view/flight_edit.jsp").forward(req, resp);
    }
}
