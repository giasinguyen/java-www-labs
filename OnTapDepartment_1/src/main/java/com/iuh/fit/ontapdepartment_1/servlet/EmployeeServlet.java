package com.iuh.fit.ontapdepartment_1.servlet;

import com.iuh.fit.ontapdepartment_1.dao.DepartmentDAO;
import com.iuh.fit.ontapdepartment_1.dao.EmployeeDAO;
import com.iuh.fit.ontapdepartment_1.model.Employee;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {

    @Resource(name = "jdbc/employees")
    private DataSource dataSource;

    private EmployeeDAO empDao;
    private DepartmentDAO deptDao;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        try {
            empDao = new EmployeeDAO(dataSource);
            deptDao = new DepartmentDAO(dataSource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
                List<Employee> all = empDao.getAllEmployees();
                req.setAttribute("employees", all);
                req.getRequestDispatcher("employee-list.jsp").forward(req, resp);
                break;

            case "new":
                req.setAttribute("departments", deptDao.getAll());
                req.getRequestDispatcher("employee-form.jsp").forward(req, resp);
                break;

            case "edit":
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("employee", empDao.getEmployeeById(id));
                req.setAttribute("departments", deptDao.getAll());
                req.getRequestDispatcher("employee-form.jsp").forward(req, resp);
                break;

            case "delete":
                empDao.delete(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect("employees");
                break;

            case "viewbyid": // list theo dept
                int deptId = Integer.parseInt(req.getParameter("deptId"));
                req.setAttribute("employees", empDao.getAllByDepartment(deptId));
                req.getRequestDispatcher("employee-list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        int id = (idParam == null || idParam.isBlank()) ? 0 : Integer.parseInt(idParam);

        String name = req.getParameter("name");
        double salary = Double.parseDouble(req.getParameter("salary"));
        int deptId = Integer.parseInt(req.getParameter("departmentId"));

        Employee emp = new Employee(id, name, deptId, salary);
        if (id > 0) empDao.update(emp); else empDao.save(emp);

        resp.sendRedirect("employees");
    }
}
