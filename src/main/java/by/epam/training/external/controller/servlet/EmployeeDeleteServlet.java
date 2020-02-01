package by.epam.training.external.controller.servlet;

import by.epam.training.external.entity.Employee;
import by.epam.training.external.service.DispatcherService;
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

@WebServlet("/dispatcher/employee/delete")
public class EmployeeDeleteServlet extends HttpServlet {
    private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private DispatcherService dispatcherService = new DispatcherService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonEmployee = readJson(req);
        Employee employee = gson.fromJson(jsonEmployee, Employee.class);
        dispatcherService.fireEmployee(employee.getId());
    }

    private String readJson(HttpServletRequest req) throws IOException {
        InputStream in = req.getInputStream();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            return reader.readLine();
        }
    }
}
