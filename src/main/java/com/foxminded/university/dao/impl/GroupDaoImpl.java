package com.foxminded.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foxminded.university.dao.DaoFactory;
import com.foxminded.university.dao.GroupDao;
import com.foxminded.university.dao.exception.DaoException;
import com.foxminded.university.domain.Department;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;

public class GroupDaoImpl implements GroupDao {
    private DaoFactory factory = new DaoFactory();

    public Group getById(int id) {
        String sql = "select groups.id as groupId, year, groups.title as group, "
                + "departments.id AS deapartmentId, departments.title as department, "
                + "faculties.id AS facultyId, faculties.title AS faculty " + "from groups, departments, faculties "
                + "where groups.department_id = departments.id and departments.faculty_id = faculties.id and groups.id = ?;";

        ResultSet result = null;
        Group group = new Group();

        try (Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, id);
            result = statement.executeQuery();
            if (!result.isBeforeFirst()) {
                throw new DaoException("no such group found");
            }
            while (result.next()) {
                group = extractGroup(result);
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
        return group;
    }

    @Override
    public List<Group> getAll() {
        List<Group> groups = new ArrayList<>();
        String sql = "select groups.id as groupId, year, groups.title as group, "
                + "departments.id AS deapartmentId, departments.title as department, "
                + "faculties.id AS facultyId, faculties.title AS faculty " + "from groups, departments, faculties "
                + "where groups.department_id = departments.id and departments.faculty_id = faculties.id;";

        try (Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet result = statement.executeQuery();) {

            while (result.next()) {
                groups.add(extractGroup(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    @Override
    public Group add(Group group) {
        String sql = "INSERT INTO groups (year, title, department_id) VALUES (?, ?, ?) RETURNING id;";

        ResultSet result = null;

        try (Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, group.getYear());
            statement.setString(2, group.getTitle());
            statement.setInt(3, group.getDepartment().getId());
            result = statement.executeQuery();
            if (result.next()) {
                group.setId(result.getInt("id"));
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
        return group;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM groups WHERE id = ?;";

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
    public Group update(Group group) {
        String sql = "UPDATE groups SET  year = ?, title = ?, department_id = ? WHERE id = ?;";

        try (Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, group.getYear());
            statement.setString(2, group.getTitle());
            statement.setInt(3, group.getDepartment().getId());
            statement.setInt(4, group.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error while updating");
        }
        return group;
    }

    private Group extractGroup(ResultSet result) {
        Group group = new Group();
        try {
            group.setId(result.getInt("groupId"));
            group.setYear(result.getInt("year"));
            group.setTitle(result.getString("group"));

            Department department = new Department();
            department.setId(result.getInt("deapartmentId"));
            department.setTitle(result.getString("department"));

            Faculty faculty = new Faculty();
            faculty.setId(result.getInt("facultyId"));
            faculty.setTitle(result.getString("faculty"));

            department.setFaculty(faculty);
            group.setDepartment(department);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }
}
