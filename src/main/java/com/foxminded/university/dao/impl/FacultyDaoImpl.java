package com.foxminded.university.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.foxminded.university.dao.FacultyDao;
import com.foxminded.university.dao.mapper.FacultyMapper;
import com.foxminded.university.domain.Faculty;

@Repository
public class FacultyDaoImpl implements FacultyDao {
    private JdbcTemplate jdbcTemplate;    
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    private final String SQL_GET_FACULTY = "select " +
            "f.id as faculty_id, f.title as faculty_title  " +
            "from faculties as f " +
            "where f.id = ?;";
    
    private final String SQL_GET_ALL_FACULTIES = "select " +
            "f.id as faculty_id, f.title as faculty_title  " +
            "from faculties as f ;";
    
    private final String SQL_ADD_FACULTY = "INSERT INTO faculties (title) VALUES (:title) ;";
    private final String SQL_DELETE_FACULTY = "DELETE FROM faculties WHERE id = ?;";
    private final String SQL_UPDATE_FACULTY = "UPDATE faculties SET  title = ? WHERE id = ?;";
   
    @Autowired
    public FacultyDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public FacultyDaoImpl() {
    }

    @Override
    public Faculty getById(int id) {
        return jdbcTemplate.queryForObject(SQL_GET_FACULTY, new Object[] { id }, new FacultyMapper());
    }

    @Override
    public List<Faculty> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL_FACULTIES, new FacultyMapper());
    }

    @Override
    public Faculty add(Faculty faculty) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource().addValue("title", faculty.getTitle());
        namedParameterJdbcTemplate.update(SQL_ADD_FACULTY, parameters, holder, new String[] { "id" });
        faculty.setId(holder.getKey().intValue());
        return faculty;
    }

    @Override
    public boolean delete(int id) {
        jdbcTemplate.update(SQL_DELETE_FACULTY, id);
        return true;
    }

    @Override
    public Faculty update(Faculty faculty) {
        jdbcTemplate.update(SQL_UPDATE_FACULTY, faculty.getTitle(), faculty.getId());
        return faculty;
    }
}
