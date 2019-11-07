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
    private static final int DEFAULT_GROUP_ID = 0;

    @Override
    public Student getById(int id) {
	String sql = "SELECT * FROM students LEFT JOIN groups ON students.group_id = groups.id "
		+ "JOIN departments ON groups.department_id = departments.id "
		+ "JOIN faculties ON departments.faculty_id = faculties.id " + "WHERE students.id = ?;";
	ResultSet result = null;
	Student student = new Student();

	try (Connection connection = factory.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);) {

	    statement.setInt(1, id);
	    result = statement.executeQuery();
	    while (result.next()) {
		student = extractStudent(result);
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
	return student;
    }

    @Override
    public List<Student> getAll() {
	List<Student> students = new ArrayList<>();
	String sql = "SELECT * FROM students LEFT JOIN groups ON students.group_id = groups.id "
		+ "JOIN departments ON groups.department_id = departments.id;";

	try (Connection connection = factory.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();) {

	    while (result.next()) {
		students.add(extractStudent(result));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return students;
    }

    @Override
    public Student add(Student student) {
	String sql = "INSERT INTO students (first_name, last_name, group_id) VALUES (?, ?, ?) RETURNING id;";
	ResultSet result = null;

	try (Connection connection = factory.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);) {

	    statement.setString(1, student.getFirstName());
	    statement.setString(2, student.getLastName());
	    if (student.getGroup() != null) {
		statement.setInt(3, student.getGroup().getId());
	    } else {
		statement.setInt(3, DEFAULT_GROUP_ID);
	    }
	    result = statement.executeQuery();
	    if (result.next()) {
		student.setId(result.getInt("id"));
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
	return student;
    }

    @Override
    public boolean delete(int id) {
	String sql = "DELETE FROM students WHERE id = ?;";

	try (Connection connection = factory.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);) {

	    statement.setInt(1, id);
	    statement.execute();
	    return true;
	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new DaoException("Error while deleting");
	}	
    }

    @Override
    public Student update(Student student) {
	String sql = "UPDATE students SET  first_name = ?, last_name = ?, group_id = ? WHERE id = ?;";

	try (Connection connection = factory.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);) {

	    statement.setString(1, student.getFirstName());
	    statement.setString(2, student.getLastName());
	    if (student.getGroup() != null) {
		statement.setInt(3, student.getGroup().getId());
	    } else {
		statement.setInt(3, DEFAULT_GROUP_ID);
	    }
	    statement.setInt(4, student.getId());
	    statement.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new DaoException("Error while updating");
	}
	return student;
    }

    private Student extractStudent(ResultSet result) {
	Student student = new Student();

	try {
	    student.setId(result.getInt("id"));
	    student.setFirstName(result.getString("first_name"));
	    student.setLastName(result.getString("last_name"));

	    Group group = new Group();
	    group.setId(result.getInt("groups.id"));
	    group.setYear(result.getInt("groups.year"));
	    group.setTitle(result.getString("groups.title"));

	    Department department = new Department();
	    department.setId(result.getInt("departments.id"));
	    department.setTitle(result.getString("departments.title"));

	    Faculty faculty = new Faculty();
	    faculty.setId(result.getInt("faculties.id"));
	    faculty.setTitle(result.getString("faculties.title"));

	    department.setFaculty(faculty);
	    group.setDepartment(department);
	    student.setGroup(group);

	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return student;
    }
}
