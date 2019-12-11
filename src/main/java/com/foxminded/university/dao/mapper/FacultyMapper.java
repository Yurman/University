package com.foxminded.university.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.foxminded.university.domain.Faculty;

public class FacultyMapper implements RowMapper<Faculty> {

    @Override
    public Faculty mapRow(ResultSet result, int rowNum) throws SQLException {
        Faculty faculty = new Faculty();
        faculty.setId(result.getInt("faculty_id"));
        faculty.setTitle(result.getString("faculty_title"));
        return faculty;
    }
}
