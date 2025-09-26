package com.iuh.fit.ontapdepartment_1.dao;


import com.iuh.fit.ontapdepartment_1.model.Department;
import com.iuh.fit.ontapdepartment_1.util.DBUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    private final DBUtil dbutil;

    public DepartmentDAO(DataSource dataSource) { this.dbutil = new DBUtil(dataSource); }

    // LIST all
    public List<Department> getAll() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT id, name FROM departments ORDER BY id";
        try (Connection con = dbutil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                departments.add(Department.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build());
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return departments;
    }

    // GET by id
    public Department findById(int id) {
        String sql = "SELECT id, name FROM departments WHERE id=?";
        try (Connection con = dbutil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Department.builder()
                            .id(rs.getInt("id"))
                            .name(rs.getString("name"))
                            .build();
                }
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return null;
    }

    // SEARCH by name (LIKE)
    public List<Department> searchByName(String key) {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT id, name FROM departments WHERE name LIKE ? ORDER BY id";
        try (Connection con = dbutil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + key + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(Department.builder()
                            .id(rs.getInt("id"))
                            .name(rs.getString("name"))
                            .build());
                }
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }

    public void save(Department department) {
        String sql = "INSERT INTO departments(name) VALUES (?)";
        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, department.getName());
            ps.executeUpdate();
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    public void update(Department department) {
        String sql = "UPDATE departments SET name=? WHERE id=?";
        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, department.getName());
            ps.setInt(2, department.getId());
            ps.executeUpdate();
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    public void delete(int id) {
        String sql = "DELETE FROM departments WHERE id=?";
        try (Connection conn = dbutil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) { throw new RuntimeException(e); }
    }
}

