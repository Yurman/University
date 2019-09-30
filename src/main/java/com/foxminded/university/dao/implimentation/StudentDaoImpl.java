package com.foxminded.university.dao.implimentation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foxminded.university.dao.CrudDao;
import com.foxminded.university.dao.DaoFactory;
import com.foxminded.university.domain.Student;

public class StudentDaoImpl implements CrudDao<Student> {

    @Override
    public Student getById(int id) {
	Student student = new Student();
	String sql = "SELECT * FROM students WHERE id = '?'";
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet result = null;
	GroupDaoImpl group = new GroupDaoImpl();

	try {
	    DaoFactory factory = new DaoFactory();
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setInt(1, id);
	    result = statement.executeQuery();
	    while (result.next()) {
		student.setFirstName(result.getString(2));
		student.setLastName(result.getString(3));
		student.setGroup(group.getById(result.getInt(4)));
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

	return student;
    }

    @Override
    public List<Student> getAll() {
	List<Student> students = new ArrayList<>();
	String sql = "SELECT * FROM groups";
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet result = null;
	GroupDaoImpl group = new GroupDaoImpl();

	try {
	    DaoFactory factory = new DaoFactory();
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    result = statement.executeQuery();
	    while (result.next()) {
		Student student = new Student();
		student.setFirstName(result.getString(2));
		student.setLastName(result.getString(3));
		student.setGroup(group.getById(result.getInt(4)));
		students.add(student);
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
    public boolean add(Student student) {
	String sql = "INSERT INTO students (first_name, last_name, group_id) VALUES ('?', '?', '?', '?');";
	Connection connection = null;
	PreparedStatement statement = null;

	try {
	    DaoFactory factory = new DaoFactory();
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setString(1, student.getFirstName());
	    statement.setString(2, student.getLastName());
	    statement.setInt(3, student.getGroup().hashCode()); // ?
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
    public boolean delete(int id) {
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
    public boolean update(Student student) {
	String sql = "UPDATE students SET  first_name = '?', last_name = '?', group_id = '?' WHERE id = '?'";
	Connection connection = null;
	PreparedStatement statement = null;

	try {
	    DaoFactory factory = new DaoFactory();
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setString(1, student.getFirstName());
	    statement.setString(2, student.getLastName());
	    statement.setInt(3, student.getGroup().hashCode());// ?
	    statement.setInt(4, student.hashCode());// ?
	    statement.executeQuery();
	} catch (SQLException e) {
	    e.printStackTrace();
	    return false;
	}

	return true;
    }
}
