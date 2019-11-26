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
        String sql = "select " + 
                "s.id as student_id, " + 
                "first_name, " + 
                "last_name, " + 
                "g.id as group_id, " + 
                "year, " + 
                "g.title as group_title, " + 
                "d.id as department_id, " + 
                "d.title as department_title, " + 
                "f.id as faculty_id, " + 
                "f.title as faculty_title " + 
                "from students as s, groups as g, departments as d, faculties as f " + 
                "where s.group_id = g.id " + 
                "and g.department_id = d.id " + 
                "and d.faculty_id = f.id " + 
                "and s.id = ?;";

        ResultSet result = null;
        Student student = new Student();

        try (Connection connection = factory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setInt(1, id);
            result = statement.executeQuery();
            if (!result.isBeforeFirst()) {
                throw new DaoException("no such student found");
            }
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
        String sql = "select " + 
                "s.id as student_id, " + 
                "first_name, " + 
                "last_name, " + 
                "g.id as group_id, " + 
                "year, " + 
                "g.title as group_title, " + 
                "d.id as department_id, " + 
                "d.title as department_title, " + 
                "f.id as faculty_id, " + 
                "f.title as faculty_title " + 
                "from students as s, groups as g, departments as d, faculties as f " + 
                "where s.group_id = g.id " + 
                "and g.department_id = d.id " + 
                "and d.faculty_id = f.id;";

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
            }
            result = statement.executeQuery();
            while (result.next()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error while deleting");
        }
        return true;
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
            }
            statement.setInt(4, student.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error while updating");
        }
        return student;
    }

    private Student extractStudent(ResultSet result) {
        Student student = new Student();
        
        try {
            student.setId(result.getInt("student_id"));
            student.setFirstName(result.getString("first_name"));
            student.setLastName(result.getString("last_name"));

            Group group = new Group();
            group.setId(result.getInt("group_id"));
            group.setYear(result.getInt("year"));
            group.setTitle(result.getString("group_title"));

            Department department = new Department();
            department.setId(result.getInt("department_id"));
            department.setTitle(result.getString("department_title"));

            Faculty faculty = new Faculty();
            faculty.setId(result.getInt("faculty_id"));
            faculty.setTitle(result.getString("faculty_title"));

            department.setFaculty(faculty);
            group.setDepartment(department);
            student.setGroup(group);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
}
