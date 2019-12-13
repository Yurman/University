package com.foxminded.university.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.foxminded.university.dao.DepartmentDao;
import com.foxminded.university.dao.mapper.DepartmentMapper;
import com.foxminded.university.domain.Department;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
    private JdbcTemplate jdbcTemplate;    
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    private final String SQL_GET_DEPARTMENT = "select " +
            "d.id as department_id, d.title as department_title, d.faculty_id, " +
            "f.id as faculty_id, f.title as faculty_title  " +
            "from " +
            "departments as d " +
            "left join faculties as f on d.faculty_id = f.id " +
            "where d.id = ?;";
    
    private final String SQL_GET_ALL_DEPARTMENTS = "select " +
            "d.id as department_id, d.title as department_title, d.faculty_id, " +
            "f.id as faculty_id, f.title as faculty_title  " +
            "from " +
            "departments as d " +
            "left join faculties as f on d.faculty_id = f.id; ";
    
    private final String SQL_ADD_DEPARTMENT = "INSERT INTO departments (title, faculty_id) VALUES (:title, :faculty_id) ;";
    private final String SQL_DELETE_DEPARTMENT = "DELETE FROM departments WHERE id = ?;";
    private final String SQL_UPDATE_DEPARTMENT = "UPDATE departments SET  title = ?, faculty_id = ? WHERE id = ?;";    

    @Autowired
    public DepartmentDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public DepartmentDaoImpl() {
    }

    @Override
    public Department getById(int id) {
        return jdbcTemplate.queryForObject(SQL_GET_DEPARTMENT, new Object[] { id }, new DepartmentMapper());
    }

    @Override
    public List<Department> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL_DEPARTMENTS, new DepartmentMapper());
    }

    @Override
    public Department add(Department department) {
        KeyHolder holder = new GeneratedKeyHolder();
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("title", department.getTitle());
        if (department.getFaculty() == null) {
            parameters.addValue("faculty_id", null);
        } else {
            parameters.addValue("faculty_id", department.getFaculty().getId());
        }
        namedParameterJdbcTemplate.update(SQL_ADD_DEPARTMENT, parameters, holder, new String[] { "id" });
        department.setId(holder.getKey().intValue());
        return department;
    }

    @Override
    public boolean delete(int id) {
        jdbcTemplate.update(SQL_DELETE_DEPARTMENT, id);
        return true;
    }

    @Override
    public Department update(Department department) {
        if (department.getFaculty() == null) {
            jdbcTemplate.update(SQL_UPDATE_DEPARTMENT, department.getTitle(),null, department.getId());
            return department;
        } else {
            jdbcTemplate.update(SQL_UPDATE_DEPARTMENT, department.getTitle(), department.getFaculty().getId(),
                    department.getId());
            return department;
        }
    }
}
