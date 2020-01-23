package by.epam.training.external.controller.servlet;

import by.epam.training.external.entity.Flight;
import by.epam.training.external.service.Service;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class FlightSaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Service service = Service.INSTANCE;
        BufferedReader reader = req.getReader();
        String jsonFlight;
        resp.setContentType("text/plain; charset=UTF-8");
        if (reader != null) {
            jsonFlight = reader.readLine();
        } else {
            resp.getWriter().print("fail");
            return;
        }
        Gson gson = new Gson();
        Flight bobtailFlight = gson.fromJson(jsonFlight, Flight.class);

        if (bobtailFlight.getId() > 0) {
            service.update(bobtailFlight);
        } else {
            service.create(bobtailFlight);
        }
        resp.getWriter().print("ok");
    }
}
