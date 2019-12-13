package com.foxminded.university.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.foxminded.university.domain.Faculty;

public class FacultyMapper implements RowMapper<Faculty> {

    @Override
    public Faculty mapRow(ResultSet result, int rowNum) throws SQLException {
        return DataMapper.mapFaculty(result);
    }
}
