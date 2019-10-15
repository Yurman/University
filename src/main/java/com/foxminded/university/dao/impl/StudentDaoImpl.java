package com.foxminded.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foxminded.university.dao.DaoFactory;
import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.dao.exception.DaoException;
import com.foxminded.university.domain.Department;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;

public class StudentDaoImpl implements StudentDao {
    private DaoFactory factory = new DaoFactory();

    @Override
    public Student getById(int id) {
	Student student = new Student();
	String sql = "SELECT * FROM students JOIN groups ON students.group_id = groups.id JOIN departments ON groups.department_id = departments.id JOIN faculties ON departments.faculty_id = faculties.id WHERE students.id = ?;";
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet result = null;

	try {
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setInt(1, id);
	    result = statement.executeQuery();

	    while (result.next()) {
		student.setId(result.getInt("id"));
		student.setFirstName(result.getString("first_name"));
		student.setLastName(result.getString("last_name"));

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
		student.setGroup(group);
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
	String sql = "SELECT * FROM students JOIN groups ON students.group_id = groups.id JOIN departments ON groups.department_id = departments.id JOIN faculties ON departments.faculty_id = faculties.id";
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet result = null;

	try {
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    result = statement.executeQuery();

	    while (result.next()) {
		Student student = new Student();
		student.setId(result.getInt("id"));
		student.setFirstName(result.getString("first_name"));
		student.setLastName(result.getString("last_name"));

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
		student.setGroup(group);
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
    public Student add(Student student) {
	String sql = "INSERT INTO students (first_name, last_name, group_id) VALUES (?, ?, ?);";
	Connection connection = null;
	PreparedStatement statement = null;

	try {
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setString(1, student.getFirstName());
	    statement.setString(2, student.getLastName());
	    statement.setInt(3, student.getGroup().getId());
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
	return getById(student.getId());
    }

    @Override
    public boolean delete(int id) {
	String sql = "DELETE FROM students WHERE id = ?";
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
    public Student update(Student student) {
	String sql = "UPDATE students SET  first_name = ?, last_name = ?, group_id = ? WHERE id = ?";
	Connection connection = null;
	PreparedStatement statement = null;

	try {
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    statement.setString(1, student.getFirstName());
	    statement.setString(2, student.getLastName());
	    statement.setInt(3, student.getGroup().getId());
	    statement.setInt(4, student.getId());
	    statement.executeUpdate();
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
	return getById(student.getId());
    }
}
