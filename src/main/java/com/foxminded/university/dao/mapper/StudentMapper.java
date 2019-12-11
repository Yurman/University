package com.foxminded.university.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.foxminded.university.domain.Department;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;

public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet result, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(result.getInt("student_id"));
        student.setFirstName(result.getString("first_name"));
        student.setLastName(result.getString("last_name"));
        if (result.getInt("group_id") != 0) {
            student.setGroup(mapGroup(result));
        }
        return student;
    }

    private Group mapGroup(ResultSet result) throws SQLException {
        Group group = new Group();
        group.setId(result.getInt("group_id"));
        group.setYear(result.getInt("year"));
        group.setTitle(result.getString("group_title"));
        if (result.getInt("department_id") != 0) {
            group.setDepartment(mapDepartment(result));
        }
        return group;
    }

    private Department mapDepartment(ResultSet result) throws SQLException {
        Department department = new Department();
        department.setId(result.getInt("department_id"));
        department.setTitle(result.getString("department_title"));
        if (result.getInt("faculty_id") != 0) {
            department.setFaculty(mapFaculty(result));
        }
        return department;
    }

    private Faculty mapFaculty(ResultSet result) throws SQLException {
        Faculty faculty = new Faculty();
        faculty.setId(result.getInt("faculty_id"));
        faculty.setTitle(result.getString("faculty_title"));
        return faculty;
    }
}
