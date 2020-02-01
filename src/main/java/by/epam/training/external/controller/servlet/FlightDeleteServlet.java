package by.epam.training.external.controller.servlet;

import by.epam.training.external.dto.FlightDto;
import by.epam.training.external.service.AdministratorService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@WebServlet("/flight/delete")
public class FlightDeleteServlet extends HttpServlet {
    private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private AdministratorService adminService = new AdministratorService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonFlightDto = readJson(req);
        FlightDto bobtailFlightDto = gson.fromJson(jsonFlightDto, FlightDto.class);
        adminService.cancelFlight(bobtailFlightDto);
    }

    private String readJson(HttpServletRequest req) throws IOException {
        InputStream in = req.getInputStream();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            return reader.readLine();
        }
    }
}
