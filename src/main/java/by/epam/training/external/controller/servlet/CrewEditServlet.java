package by.epam.training.external.controller.servlet;

import by.epam.training.external.controller.servlet.util.HtmlTemplateEngine;
import by.epam.training.external.service.DispatcherService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/dispatcher/edit")
public class CrewEditServlet extends HttpServlet {
    private DispatcherService dispatcherService = new DispatcherService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int flightId = Integer.parseInt(req.getParameter("flightId"));
        int crewId = Integer.parseInt(req.getParameter("crewId"));

        String pageFullPath = getServletContext().getRealPath("view/template/crew_edit.html");
        Map<String, String> parameterMap = dispatcherService.getParameterMap(flightId, crewId);
        String page = HtmlTemplateEngine.getHtmlPage(pageFullPath, parameterMap);

        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().print(page);
    }
}
