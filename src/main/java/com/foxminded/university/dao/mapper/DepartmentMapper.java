package com.foxminded.university.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.foxminded.university.domain.Department;

public class DepartmentMapper implements RowMapper<Department> {

    @Override
    public Department mapRow(ResultSet result, int rowNum) throws SQLException {
        return DataMapper.mapDepartment(result);
    }
}
