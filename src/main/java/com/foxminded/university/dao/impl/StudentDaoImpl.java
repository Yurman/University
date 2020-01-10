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

import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.dao.mapper.StudentMapper;
import com.foxminded.university.domain.Student;
import com.foxminded.university.exception.EntityNotFoundException;
import com.foxminded.university.exception.QueryNotExecuteException;

@Repository
public class StudentDaoImpl implements StudentDao {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(StudentDaoImpl.class);

    private final String SQL_GET_STUDENT = "select " +
            "s.id as student_id, s.first_name, s.last_name, " +
            "g.id as group_id, g.year, g.title as group_title, " +
            "d.id as department_id, d.title as department_title, d.faculty_id, " +
            "f.id as faculty_id, f.title as faculty_title  " +
            "from " +
            "students as s " +
            "left join groups as g on s.group_id = g.id " +
            "left join departments as d on g.department_id = d.id " +
            "left join faculties as f on d.faculty_id = f.id " +
            "where s.id = ?;";

    private final String SQL_GET_ALL_STUDENTS = "select " +
            "s.id as student_id, s.first_name, s.last_name, " +
            "g.id as group_id, g.year, g.title as group_title, " +
            "d.id as department_id, d.title as department_title, d.faculty_id, " +
            "f.id as faculty_id, f.title as faculty_title  " +
            "from " +
            "students as s " +
            "left join groups as g on s.group_id = g.id " +
            "left join departments as d on g.department_id = d.id " +
            "left join faculties as f on d.faculty_id = f.id;";

    private final String SQL_ADD_STUDENT = "INSERT INTO students (first_name, last_name, group_id) VALUES (:first_name, :last_name, :group_id) ;";
    private final String SQL_DELETE_STUDENT = "DELETE FROM students WHERE id = ?;";
    private final String SQL_UPDATE_STUDENT = "UPDATE students SET  first_name = ?, last_name = ?, group_id = ? WHERE id = ?;";

    @Autowired
    public StudentDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public StudentDaoImpl() {
    }

    @Override
    public Student getById(int id) {
        Student student = null;
        try {
            logger.debug("Searching for student with id = " + id);
            student = jdbcTemplate.queryForObject(SQL_GET_STUDENT, new Object[] { id }, new StudentMapper());
        } catch (EmptyResultDataAccessException ex) {
            logger.warn("Problem while extraction student with id = " + id, ex);
            throw new EntityNotFoundException("Problem while extraction student with id = " + id);
        }
        logger.trace("Student with id = " + id + " was found");
        return student;
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = null;
        try {
            logger.debug("Searching for all students");
            students = jdbcTemplate.query(SQL_GET_ALL_STUDENTS, new StudentMapper());
        } catch (DataAccessException ex) {
            logger.warn("Problem while students extraction", ex);
            throw new QueryNotExecuteException("Problem while students extraction");
        }
        logger.trace("All students were found");
        return students;
    }

    @Override
    public Student add(Student student) {
        KeyHolder holder = new GeneratedKeyHolder();
        Integer groupId = Objects.nonNull(student.getGroup()) ? student.getGroup().getId() : null;
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("first_name", student.getFirstName())
                .addValue("last_name", student.getLastName())
                .addValue("group_id", groupId);
        try {
            logger.debug("Trying to add new student " + student.getFirstName() + " " + student.getLastName());
            namedParameterJdbcTemplate.update(SQL_ADD_STUDENT, parameters, holder, new String[] { "id" });
            student.setId(holder.getKey().intValue());
        } catch (DataAccessException ex) {
            logger.warn("Problem while adding new student " + student.getFirstName() + " " + student.getLastName(),
                    ex);
            throw new QueryNotExecuteException(
                    "Problem while adding student" + student.getFirstName() + " " + student.getLastName());
        }
        logger.trace("New student " + student.getFirstName() + " " + student.getLastName() + "was added");
        return student;
    }

    @Override
    public boolean delete(int id) {
        try {
            logger.debug("Trying to delete student with id = " + id);
            jdbcTemplate.update(SQL_DELETE_STUDENT, id);
        } catch (EmptyResultDataAccessException ex) {
            logger.warn("Problem while deleting student with id = " + id, ex);
            throw new EntityNotFoundException("Problem while deleting student with id = " + id);
        }
        logger.trace("Student with id = " + id + " was deleted");
        return true;
    }

    @Override
    public Student update(Student student) {
        Integer groupId = Objects.nonNull(student.getGroup()) ? student.getGroup().getId() : null;
        if (student.getId() == 0) {
            logger.warn("There is no such student in database");
            throw new EntityNotFoundException("There is no such student in database");
        }
        try {
            logger.debug("Trying to update student with id = " + student.getId());
            jdbcTemplate.update(SQL_UPDATE_STUDENT, student.getFirstName(), student.getLastName(), groupId,
                    student.getId());
        } catch (EmptyResultDataAccessException ex) {
            logger.warn("Problem while student updating");
            throw new EntityNotFoundException("Problem while updating student with id = " + student.getId());
        }
        logger.trace("Student with id = " + student.getId() + " was updated");
        return student;
    }
}
