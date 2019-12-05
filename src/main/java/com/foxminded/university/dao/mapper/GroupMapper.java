package com.foxminded.university.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.foxminded.university.domain.Department;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;

public class GroupMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(ResultSet result, int rowNum) throws SQLException {

        Group group = new Group();
        Department department = new Department();
        Faculty faculty = new Faculty();

        group.setId(result.getInt("group_id"));
        group.setYear(result.getInt("year"));
        group.setTitle(result.getString("group_title"));

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
        return group;
    }
}
