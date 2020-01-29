package by.epam.training.external.controller.servlet;

import by.epam.training.external.entity.Crew;
import by.epam.training.external.entity.Employee;
import by.epam.training.external.service.DispatcherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/dispatcher/edit")
public class CrewEditServlet extends HttpServlet {
    private DispatcherService dispatcherService = new DispatcherService();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int flightId = Integer.parseInt(req.getParameter("flightId"));
        int crewId = Integer.parseInt(req.getParameter("crewId"));
        Crew crew = dispatcherService.getCrew(crewId);
        List<Employee> unusedEmployees = dispatcherService.loadAllUnusedEmployees(crew);
        req.setAttribute("flightId", flightId);
        req.setAttribute("crew", crew);
        req.setAttribute("employees", unusedEmployees);
        req.getRequestDispatcher("/view/crew_edit.jsp").forward(req, resp);
    }
}
