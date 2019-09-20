package com.foxminded.university.dao.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.foxminded.university.dao.DaoFactory;
import com.foxminded.university.dao.DepartmentDao;
import com.foxminded.university.dao.GroupDao;
import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Group;

public class PostgresGroupDao implements GroupDao {

    public Group getGroupById(int id) {
	Group group = new Group();
	String sql = "SELECT * FROM groups WHERE id = '?'";
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet result = null;
	StudentDao student = new PostgresStudentDao();
	DepartmentDao department = new PostgresDepartmentDao();

	try {
	    DaoFactory factory = new DaoFactory();
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setInt(1, id);
	    result = statement.executeQuery();
	    group.setTitle(result.getString(3));
	    group.setYear(result.getInt(2));
	    int groupId = result.getInt(1);
	    group.setStudents(student.getStudentsByGroupId(groupId));
	    int departmentId = result.getInt(4);
	    group.setDepartment(department.getDepartmentById(departmentId));
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
    public int addGroup(int id, int year, String title, int department_id) {
	String sql = "INSERT INTO groups (id, year, title, department_id) VALUES ('?', '?', '?', '?');";
	Connection connection = null;
	PreparedStatement statement = null;

	try {
	    DaoFactory factory = new DaoFactory();
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setInt(1, id);
	    statement.setInt(2, year);
	    statement.setString(3, title);
	    statement.setInt(4, department_id);
	    statement.executeQuery();
	} catch (SQLException e) {
	    e.printStackTrace();
	    return -1;
	}

	return 1;
    }

    @Override
    public boolean deleteGroup(int id) {
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
    public boolean updateGroup(int id) {
	// TODO Auto-generated method stub
	return false;
    }

}
