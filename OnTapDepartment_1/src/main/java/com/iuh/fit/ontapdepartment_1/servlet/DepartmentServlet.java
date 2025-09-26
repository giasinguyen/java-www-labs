package com.iuh.fit.ontapdepartment_1.servlet;

import com.iuh.fit.ontapdepartment_1.dao.DepartmentDAO;
import com.iuh.fit.ontapdepartment_1.dao.EmployeeDAO;
import com.iuh.fit.ontapdepartment_1.model.Department;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;


@WebServlet("/departments")
public class DepartmentServlet extends HttpServlet {
    @Resource(name = "jdbc/employees")
    private DataSource dataSource;

    private EmployeeDAO employeeDAO;
    private DepartmentDAO departmentDAO;

    @Override
    public void init() throws ServletException {
        this.employeeDAO = new EmployeeDAO(dataSource);
        this.departmentDAO = new DepartmentDAO(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":{
                String q =  req.getParameter("q");
                List<Department> departments = (q == null || q.isBlank()) ? departmentDAO.getAll() :departmentDAO.searchByName(q.trim());
                req.setAttribute("departments", departments);
                req.getRequestDispatcher("department-list.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {}
}
