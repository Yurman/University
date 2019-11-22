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
        String sql = "select students.id AS studentId, first_name AS name, last_name AS Surname, "
                + "groups.id as groupId, year, groups.title as group, "
                + "departments.id AS deapartmentId, departments.title as department, "
                + "faculties.id AS facultyId, faculties.title AS faculty "
                + "from students, groups, departments, faculties "
                + "where students.group_id = groups.id and groups.department_id = departments.id "
                + "and departments.faculty_id = faculties.id and students.id = ?;";

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
        String sql = "select students.id AS studentId, first_name AS name, last_name AS Surname, "
                + "groups.id as groupId, year, groups.title as group, "
                + "departments.id AS deapartmentId, departments.title as department, "
                + "faculties.id AS facultyId, faculties.title AS faculty "
                + "from students, groups, departments, faculties "
                + "where students.group_id = groups.id and groups.department_id = departments.id and departments.faculty_id = faculties.id;";

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
            student.setId(result.getInt("studentId"));
            student.setFirstName(result.getString("name"));
            student.setLastName(result.getString("Surname"));

            Group group = new Group();
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
            student.setGroup(group);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
}
