package com.foxminded.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foxminded.university.dao.DaoFactory;
import com.foxminded.university.dao.FacultyDao;
import com.foxminded.university.dao.exception.DaoException;
import com.foxminded.university.domain.Faculty;

public class FacultyDaoImpl implements FacultyDao {
    private DaoFactory factory = new DaoFactory();

    public Faculty getById(int id) {
        String sql = "select faculties.id AS facultyId, faculties.title AS faculty from faculties where id = ?;";

        ResultSet result = null;
        Faculty faculty = new Faculty();

        try (Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, id);
            result = statement.executeQuery();
            if (!result.isBeforeFirst() ) {    
                throw new DaoException("no such faculty found"); 
            } 
            while (result.next()) {
                faculty = extractFaculty(result);
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
        return faculty;
    }

    @Override
    public List<Faculty> getAll() {
        List<Faculty> faculties = new ArrayList<>();
        String sql = "select faculties.id AS facultyId, faculties.title AS faculty from faculties;";

        try (Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet result = statement.executeQuery();) {

            while (result.next()) {
                faculties.add(extractFaculty(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return faculties;
    }

    @Override
    public Faculty add(Faculty faculty) {
        String sql = "INSERT INTO faculties (title) VALUES (?) RETURNING id;";

        ResultSet result = null;

        try (Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, faculty.getTitle());
            result = statement.executeQuery();
            if (result.next()) {
                faculty.setId(result.getInt("id"));
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
        return faculty;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM faculties WHERE id = ?;";

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
    public Faculty update(Faculty faculty) {
        String sql = "UPDATE faculties SET  title = ? WHERE id = ?;";

        try (Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, faculty.getTitle());
            statement.setInt(2, faculty.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error while updating");
        }
        return faculty;
    }

    private Faculty extractFaculty(ResultSet result) {
        Faculty faculty = new Faculty();
        try {
            faculty.setId(result.getInt("facultyId"));
            faculty.setTitle(result.getString("faculty"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return faculty;
    }
}
