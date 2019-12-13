package com.foxminded.university.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.foxminded.university.domain.Student;

public class StudentMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet result, int rowNum) throws SQLException {
        return DataMapper.mapStudent(result);
    }
}
