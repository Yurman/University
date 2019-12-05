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
        Group group = new Group();
        Department department = new Department();
        Faculty faculty = new Faculty();

        student.setId(result.getInt("student_id"));
        student.setFirstName(result.getString("first_name"));
        student.setLastName(result.getString("last_name"));
        if (result.getInt("faculty_id") != 0) {
            faculty.setId(result.getInt("faculty_id"));
            faculty.setTitle(result.getString("faculty_title"));

            department.setFaculty(faculty);
        }
        if (result.getInt("department_id") != 0) {
            department.setId(result.getInt("department_id"));
            department.setTitle(result.getString("department_title"));

            group.setDepartment(department);
        }
        if (result.getInt("group_id") != 0) {
            group.setId(result.getInt("group_id"));
            group.setYear(result.getInt("year"));
            group.setTitle(result.getString("group_title"));

            student.setGroup(group);
        }
        return student;
    }
}
