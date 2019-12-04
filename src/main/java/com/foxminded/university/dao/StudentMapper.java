package com.foxminded.university.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.foxminded.university.domain.Department;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;

public class StudentMapper implements RowMapper<Student>{

    @Override
    public Student mapRow(ResultSet result, int rowNum) throws SQLException {
        Student student = new Student();
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
        
        return student;
    }
}
