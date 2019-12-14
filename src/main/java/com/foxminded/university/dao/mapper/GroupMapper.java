package com.foxminded.university.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.foxminded.university.domain.Group;

public class GroupMapper implements RowMapper<Group> {

    @Override
    public Group mapRow(ResultSet result, int rowNum) throws SQLException {
        return DataMapper.mapGroup(result);
    }
}
