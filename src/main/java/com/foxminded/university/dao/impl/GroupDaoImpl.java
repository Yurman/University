package com.foxminded.university.dao.impl;

import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.foxminded.university.dao.GroupDao;
import com.foxminded.university.dao.mapper.GroupMapper;
import com.foxminded.university.domain.Group;
import com.foxminded.university.exception.EntityNotFoundException;
import com.foxminded.university.exception.QueryNotExecuteException;

@Repository
public class GroupDaoImpl implements GroupDao {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(GroupDaoImpl.class);

    private static final String SQL_GET_GROUP = "select " +
            "g.id as group_id, g.year, g.title as group_title, " +
            "d.id as department_id, d.title as department_title, d.faculty_id, " +
            "f.id as faculty_id, f.title as faculty_title  " +
            "from " +
            "groups as g left join departments as d on g.department_id = d.id " +
            "left join faculties as f on d.faculty_id = f.id " +
            "where g.id = ?;";

    private static final String SQL_GET_ALL_GROUPS = "select " +
            "g.id as group_id, g.year, g.title as group_title, " +
            "d.id as department_id, d.title as department_title, d.faculty_id, " +
            "f.id as faculty_id, f.title as faculty_title  " +
            "from " +
            "groups as g left join departments as d on g.department_id = d.id " +
            "left join faculties as f on d.faculty_id = f.id; ";

    private static final String SQL_ADD_GROUP = "INSERT INTO groups (title, year, department_id) VALUES (:title, :year, :department_id) ;";
    private static final String SQL_DELETE_GROUP = "DELETE FROM groups WHERE id = ?;";
    private static final String SQL_UPDATE_GROUP = "UPDATE groups SET  title = ?, year = ?, department_id = ? WHERE id = ?;";

    @Autowired
    public GroupDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public GroupDaoImpl() {
    }

    @Override
    public Group getById(int id) {
        logger.debug("getById() [{}]", id);
        Group group = null;
        try {
            group = jdbcTemplate.queryForObject(SQL_GET_GROUP, new Object[] { id }, new GroupMapper());
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException();
        } catch (DataAccessException exc) {
            throw new QueryNotExecuteException();
        }
        logger.trace("Result: [{}] ", group);
        return group;
    }

    @Override
    public List<Group> getAll() {
        logger.debug("getAll()");
        List<Group> groups = null;
        try {
            groups = jdbcTemplate.query(SQL_GET_ALL_GROUPS, new GroupMapper());
        } catch (DataAccessException exc) {
            throw new QueryNotExecuteException();
        }
        logger.trace("Result: [{}] ", groups.size());
        return groups;
    }

    @Override
    public Group add(Group group) {
        logger.debug("add() [{}]", group);
        KeyHolder holder = new GeneratedKeyHolder();
        Integer departmentId = Objects.nonNull(group.getDepartment()) ? group.getDepartment().getId() : null;
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("title", group.getTitle())
                .addValue("year", group.getYear())
                .addValue("department_id", departmentId);
        try {
            namedParameterJdbcTemplate.update(SQL_ADD_GROUP, parameters, holder, new String[] { "id" });
            group.setId(holder.getKey().intValue());
        } catch (DataAccessException exc) {
            throw new QueryNotExecuteException();
        }
        logger.trace("Result: [{}] ", group);
        return group;
    }

    @Override
    public boolean delete(int id) {
        logger.debug("delete() [{}]", id);
        try {
            jdbcTemplate.update(SQL_DELETE_GROUP, id);
        } catch (DataAccessException exc) {
            throw new QueryNotExecuteException();
        }
        logger.trace("Result: [true] ");
        return true;
    }

    @Override
    public Group update(Group group) {
        logger.debug("update() [{}]", group);
        Integer departmentId = Objects.nonNull(group.getDepartment()) ? group.getDepartment().getId() : null;
        if (group.getId() == 0) {
            throw new EntityNotFoundException("There is no such group in database");
        }
        try {
            jdbcTemplate.update(SQL_UPDATE_GROUP, group.getTitle(), group.getYear(), departmentId, group.getId());
        } catch (DataAccessException exc) {
            throw new QueryNotExecuteException();
        }
        logger.trace("Result: [{}] ", group);
        return group;
    }
}
