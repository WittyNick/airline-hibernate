package by.epam.training.external.controller.servlet;

import by.epam.training.external.entity.Employee;
import by.epam.training.external.service.EmployeeService;
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

@WebServlet("/dispatcher/employee/add")
public class EmployeeAddServlet extends HttpServlet {
    private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String jsonEmployee = readJson(req);
        Employee employee = gson.fromJson(jsonEmployee, Employee.class);
        employeeService.saveEmployee(employee);
        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().print(gson.toJson(employee));
    }

    private String readJson(HttpServletRequest req) throws IOException {
        InputStream in = req.getInputStream();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            return reader.readLine();
        }
    }
}