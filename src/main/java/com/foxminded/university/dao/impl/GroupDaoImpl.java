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
	String sql = "SELECT * FROM groups JOIN departments ON groups.department_id = departments.id"
		+ " JOIN faculties ON departments.faculty_id = faculties.id"
		+ "WHERE groups.id = ?;";
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet result = null;
	Group group = new Group();

	try {
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setInt(1, id);
	    result = statement.executeQuery();
	    group = assembleGroup(result).get(0);
	} catch (SQLException e) {
	    e.printStackTrace();

	} finally {
	    try {
		result.close();
		statement.close();
		connection.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	return group;
    }

    @Override
    public List<Group> getAll() {
	List<Group> groups = new ArrayList<>();
	String sql = "SELECT * FROM groups JOIN departments ON groups.department_id = departments.id"
		+ " JOIN faculties ON departments.faculty_id = faculties.id;";
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet result = null;

	try {
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    result = statement.executeQuery();
	    groups = assembleGroup(result);
	} catch (SQLException e) {
	    e.printStackTrace();
	} finally {
	    try {
		result.close();
		statement.close();
		connection.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	return groups;
    }

    @Override
    public Group add(Group group) {
	String sql = "INSERT INTO groups (year, title, department_id) VALUES (?, ?, ?, ?);";
	Connection connection = null;
	PreparedStatement statement = null;

	try {
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setInt(1, group.getYear());
	    statement.setString(2, group.getTitle());
	    statement.setInt(3, group.getDepartment().getId());
	    statement.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new DaoException("Error while adding");
	} finally {
	    try {
		statement.close();
		connection.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	return getById(group.getId());
    }

    @Override
    public boolean delete(int id) {
	String sql = "DELETE FROM groups WHERE id = ?";
	Connection connection = null;
	PreparedStatement statement = null;

	try {
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setInt(1, id);
	    statement.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new DaoException("Error while deleting");
	} finally {
	    try {
		statement.close();
		connection.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	return true;
    }

    @Override
    public Group update(Group group) {
	String sql = "UPDATE groups SET  year = '?', title = '?', department_id = '?' WHERE id = '?'";
	Connection connection = null;
	PreparedStatement statement = null;

	try {
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setInt(1, group.getYear());
	    statement.setString(2, group.getTitle());
	    statement.setInt(3, group.getDepartment().getId());
	    statement.setInt(4, group.getId());
	    statement.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new DaoException("Error while updating");
	} finally {
	    try {
		statement.close();
		connection.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
	return group;
    }

    private List<Group> assembleGroup(ResultSet result) {
	List<Group> groups = new ArrayList<>();
	try {
	    while (result.next()) {
		Group group = new Group();
		group.setId(result.getInt("id"));
		group.setYear(result.getInt("year"));
		group.setTitle(result.getString("title"));

		Department department = new Department();
		department.setId(result.getInt("id"));
		department.setTitle(result.getString("title"));

		Faculty faculty = new Faculty();
		faculty.setId(result.getInt("id"));
		faculty.setTitle(result.getString("title"));

		department.setFaculty(faculty);
		group.setDepartment(department);
		groups.add(group);
	    }
	} catch (SQLException e) {

	    e.printStackTrace();
	}
	return groups;
    }
}
