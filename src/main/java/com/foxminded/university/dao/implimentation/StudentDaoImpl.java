package com.foxminded.university.dao.implimentation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foxminded.university.dao.DaoFactory;
import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Department;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;

public class StudentDaoImpl implements StudentDao {

    @Override
    public Student getById(int id) {
	Student student = new Student();
	String sql = "SELECT * FROM students JOIN groups ON students.group_id = groups.id JOIN departments ON groups.department_id = departments.id JOIN faculties ON departments.faculty_id = faculties.id WHERE students.id = '?';";
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
		student.setId(id);
		student.setFirstName(result.getString(2));
		student.setLastName(result.getString(3));
		Group group = new Group();
		group.setId(result.getInt(5));
		group.setYear(result.getInt(6));
		group.setTitle(result.getString(7));
		Department department = new Department();
		department.setId(result.getInt(9));
		department.setTitle(result.getString(10));
		Faculty faculty = new Faculty();
		faculty.setId(result.getInt(12));
		faculty.setTitle(result.getString(13));
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
	    DaoFactory factory = new DaoFactory();
	    connection = factory.getConnection();
	    statement = connection.prepareStatement(sql);
	    result = statement.executeQuery();

	    while (result.next()) {
		Student student = new Student();
		student.setId(result.getInt(1));
		student.setFirstName(result.getString(2));
		student.setLastName(result.getString(3));
		Group group = new Group();
		group.setId(result.getInt(5));
		group.setYear(result.getInt(6));
		group.setTitle(result.getString(7));
		Department department = new Department();
		department.setId(result.getInt(9));
		department.setTitle(result.getString(10));
		Faculty faculty = new Faculty();
		faculty.setId(result.getInt(12));
		faculty.setTitle(result.getString(13));
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
	    statement.setInt(3, student.getGroup().getId());
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
	    statement.setInt(3, student.getGroup().getId());
	    statement.setInt(4, student.getId());
	    statement.executeQuery();
	} catch (SQLException e) {
	    e.printStackTrace();
	    return false;
	}

	return true;
    }
}
