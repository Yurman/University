package com.foxminded.university.dao.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foxminded.university.dao.DaoFactory;
import com.foxminded.university.dao.GroupDao;
import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Student;

public class PostgresStudentDao implements StudentDao {

    @Override
    public Student getById(int id) {
	Student student = new Student();
	String sql = "SELECT * FROM students WHERE id = '?'";
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet result = null;
	GroupDao group = new PostgresGroupDao();

	try {
	    DaoFactory factory = new DaoFactory();
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setInt(1, id);
	    result = statement.executeQuery();
	    student.setFirstName(result.getString(2));
	    student.setLastName(result.getString(3));
	    student.setGroup(group.getGroupById(result.getInt(4)));

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

	return student;
    }

    @Override
    public List<Student> getStudentsByGroupId(int id) {
	List<Student> students = new ArrayList<>();
	String sql = "SELECT * FROM students WHERE group_id = '?'";
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
		students.add((Student) result.getObject(1));
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

	return students;
    }

    @Override
    public int addStudent(int id, String firstName, String lastName, int group_id) {
	String sql = "INSERT INTO students (id, first_name, last_name, group_id) VALUES ('?', '?', '?', '?');";
	Connection connection = null;
	PreparedStatement statement = null;

	try {
	    DaoFactory factory = new DaoFactory();
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setInt(1, id);
	    statement.setString(2, firstName);
	    statement.setString(3, lastName);
	    statement.setInt(4, group_id);
	    statement.executeQuery();
	} catch (SQLException e) {
	    e.printStackTrace();
	    return -1;
	} finally {
	    try {
		statement.close();
		connection.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}

	return 1;
    }

    @Override
    public boolean deleteStudent(int id) {
	String sql = "DELETE FROM students WHERE id = '?'";
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
    public boolean updateStudent(int id, String firstName, String lastName, int group_id) {
	// TODO Auto-generated method stub
	return false;
    }

}
