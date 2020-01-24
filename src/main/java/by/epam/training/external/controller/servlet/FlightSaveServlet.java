package by.epam.training.external.controller.servlet;

import by.epam.training.external.entity.dto.FlightDto;
import by.epam.training.external.service.AdministratorService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/administrator/save")
public class FlightSaveServlet extends HttpServlet {
    private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private AdministratorService adminService = new AdministratorService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        String jsonFlightDto = readJson(req);
        if (jsonFlightDto.isEmpty()) {
            resp.getWriter().print("fail");
            return;
        }
        FlightDto bobtailFlightDto = gson.fromJson(jsonFlightDto, FlightDto.class);
        adminService.createOrUpdateFlight(bobtailFlightDto);
        resp.getWriter().print("ok");
    }

    private String readJson(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        if (reader == null) {
            return "";
        }
        return reader.readLine();
    }
}
