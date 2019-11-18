package com.foxminded.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foxminded.university.dao.DaoFactory;
import com.foxminded.university.dao.DepartmentDao;
import com.foxminded.university.dao.exception.DaoException;
import com.foxminded.university.domain.Department;
import com.foxminded.university.domain.Faculty;

public class DepartmentDaoImpl implements DepartmentDao {
    private DaoFactory factory = new DaoFactory();

    public Department getById(int id) {
        String sql = "SELECT * FROM  departments JOIN faculties ON departments.faculty_id = faculties.id "
                + "WHERE departments.id = ?;";

        ResultSet result = null;
        Department department = new Department();

        try (Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, id);
            result = statement.executeQuery();
            while (result.next()) {
                department = extractDepartment(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return department;
    }

    @Override
    public List<Department> getAll() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM departments JOIN faculties ON departments.faculty_id = faculties.id;";

        try (Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet result = statement.executeQuery();) {

            while (result.next()) {
                departments.add(extractDepartment(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public Department add(Department department) {
        String sql = "INSERT INTO departments (title, faculty_id) VALUES (?, ?) RETURNING id;";

        ResultSet result = null;

        try (Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, department.getTitle());
            statement.setInt(2, department.getFaculty().getId());
            result = statement.executeQuery();
            if (result.next()) {
                department.setId(result.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error while adding");
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return department;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM departments WHERE id = ?;";

        try (Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error while deleting");
        }
        return true;
    }

    @Override
    public Department update(Department department) {
        String sql = "UPDATE departments SET  title = ?, faculty_id = ? WHERE id = ?;";

        try (Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, department.getTitle());
            statement.setInt(2, department.getFaculty().getId());
            statement.setInt(3, department.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error while updating");
        }
        return department;
    }

    private Department extractDepartment(ResultSet result) {
        Department department = new Department();
        try {
            department.setId(result.getInt(1));
            department.setTitle(result.getString(2));

            Faculty faculty = new Faculty();
            faculty.setId(result.getInt(4));
            faculty.setTitle(result.getString(5));

            department.setFaculty(faculty);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }
}
