package by.epam.training.external.controller.servlet;

import by.epam.training.external.controller.servlet.util.HtmlTemplateEngine;
import by.epam.training.external.service.AdministratorService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/administrator/edit")
public class FlightEditServlet extends HttpServlet {
    private AdministratorService adminService = new AdministratorService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int flightId = Integer.parseInt(req.getParameter("flightId"));
        Map<String, String> parameterMap = adminService.getPageParameters(flightId);
        String pageFullPath = getServletContext().getRealPath("view/template/flight-edit.html");
        String page = HtmlTemplateEngine.getHtmlPage(pageFullPath, parameterMap);
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(page);
    }
}
