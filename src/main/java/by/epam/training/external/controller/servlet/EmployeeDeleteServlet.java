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

@WebServlet("/dispatcher/employee/delete")
public class EmployeeDeleteServlet extends HttpServlet {
    private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private DispatcherService dispatcherService = new DispatcherService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        String jsonEmployee = readJson(req);
        if (jsonEmployee.isEmpty()) {
            resp.getWriter().print("fail");
            return;
        }
        Employee employee = gson.fromJson(jsonEmployee, Employee.class);
        dispatcherService.fireEmployee(employee.getId());
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
