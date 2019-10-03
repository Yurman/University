package com.foxminded.university.dao.implimentation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foxminded.university.dao.DaoFactory;
import com.foxminded.university.dao.GroupDao;
import com.foxminded.university.domain.Department;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;

public class GroupDaoImpl implements GroupDao {

    public Group getById(int id) {
	Group group = new Group();
	String sql = "SELECT * FROM groups JOIN departments ON groups.department_id = departments.id JOIN faculties ON departments.faculty_id = faculties.id WHERE groups.id = '?';";
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet result = null;

	try {
	    DaoFactory factory = new DaoFactory();
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setInt(1, id);
	    result = statement.executeQuery();

	    while (result.next()) {
		group.setId(id);
		group.setYear(result.getInt(2));
		group.setTitle(result.getString(3));
		Department department = new Department();
		department.setId(result.getInt(5));
		department.setTitle(result.getString(6));
		Faculty faculty = new Faculty();
		faculty.setId(result.getInt(8));
		faculty.setTitle(result.getString(9));
		department.setFaculty(faculty);
		group.setDepartment(department);
	    }
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
	String sql = "SELECT * FROM groups JOIN departments ON groups.department_id = departments.id JOIN faculties ON departments.faculty_id = faculties.id ;";
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet result = null;

	try {
	    DaoFactory factory = new DaoFactory();
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    result = statement.executeQuery();

	    while (result.next()) {
		Group group = new Group();
		group.setId(result.getInt(1));
		group.setYear(result.getInt(2));
		group.setTitle(result.getString(3));
		Department department = new Department();
		department.setId(result.getInt(5));
		department.setTitle(result.getString(6));
		Faculty faculty = new Faculty();
		faculty.setId(result.getInt(8));
		faculty.setTitle(result.getString(9));
		department.setFaculty(faculty);
		group.setDepartment(department);
		groups.add(group);
	    }
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
    public boolean add(Group group) {
	String sql = "INSERT INTO groups (id, year, title, department_id) VALUES ('?', '?', '?', '?');";
	Connection connection = null;
	PreparedStatement statement = null;

	try {
	    DaoFactory factory = new DaoFactory();
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setInt(1, group.hashCode());
	    statement.setInt(2, group.getYear());
	    statement.setString(3, group.getTitle());
	    statement.setInt(4, group.getDepartment().getId());
	    statement.executeQuery();
	} catch (SQLException e) {
	    e.printStackTrace();
	    return false;
	}

	return true;
    }

    @Override
    public boolean delete(int id) {
	String sql = "DELETE FROM groups WHERE id = '?'";
	Connection connection = null;
	PreparedStatement statement = null;

	try {
	    DaoFactory factory = new DaoFactory();
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setInt(1, id);
	    statement.executeQuery();
	} catch (SQLException e) {
	    e.printStackTrace();
	    return false;
	}
	return true;
    }

    @Override
    public boolean update(Group group) {
	String sql = "UPDATE groups SET  year = '?', title = '?', department_id = '?' WHERE id = '?'";
	Connection connection = null;
	PreparedStatement statement = null;

	try {
	    DaoFactory factory = new DaoFactory();
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setInt(1, group.getYear());
	    statement.setString(2, group.getTitle());
	    statement.setInt(3, group.getDepartment().getId());
	    statement.setInt(4, group.hashCode());
	    statement.executeQuery();
	} catch (SQLException e) {
	    e.printStackTrace();
	    return false;
	}

	return true;
    }
}
