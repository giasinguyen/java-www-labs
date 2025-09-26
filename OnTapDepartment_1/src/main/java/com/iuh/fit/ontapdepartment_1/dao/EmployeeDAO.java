package com.iuh.fit.ontapdepartment_1.dao;

import com.iuh.fit.ontapdepartment_1.model.Employee;
import com.iuh.fit.ontapdepartment_1.util.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private DBUtil dbUtil;

    public EmployeeDAO(DataSource dataSource) {
        this.dbUtil = new DBUtil(dataSource);
    }

    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employees  WHERE id = ?";

        try (Connection connection = dbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery();
        ) {
            statement.setInt(1, id);
            while (resultSet.next()) {
                return Employee
                        .builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .salary(resultSet.getInt("salary"))
                        .departmentId(resultSet.getInt("department_id"))
                        .build();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Connection connection = dbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                employees.add(Employee
                        .builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .salary(resultSet.getInt("salary"))
                        .departmentId(resultSet.getInt("department_id"))
                        .build());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employees;
    }

    public List<Employee> getAllByDepartment(int deptId) {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE department_id = ?";
        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, deptId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    employeeList.add(Employee
                            .builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("name"))
                            .salary(resultSet.getDouble("salary"))
                            .departmentId(resultSet.getInt("department_id"))
                            .build());
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }

    public void save(Employee emp) {
        String sql = "INSERT INTO employees(name, salary, department_id) VALUES (?,?,?)";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setDouble(2, emp.getSalary());
            ps.setInt(3, emp.getDepartmentId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Employee emp) {
        String sql = "UPDATE employees SET name=?, salary=?, department_id=? WHERE id=?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getName());
            ps.setDouble(2, emp.getSalary());
            ps.setInt(3, emp.getDepartmentId());
            ps.setInt(4, emp.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM employees WHERE id=?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
