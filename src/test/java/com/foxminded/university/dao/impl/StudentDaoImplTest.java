package com.foxminded.university.dao.impl;

import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.foxminded.university.dao.DaoFactory;

public class StudentDaoImplTest {

    private StudentDaoImpl studentDao = new StudentDaoImpl();
    private DaoFactory factory = new DaoFactory();
    Flyway flyway = new Flyway();

    @Before
    public void setUp() throws Exception {

	BasicDataSource dataSource = new BasicDataSource();
	dataSource.setDriverClassName("org.postgresql.Driver");
	dataSource.setUrl("jdbc:postgresql://localhost:5433/test_university");
	dataSource.setUsername("admin");
	dataSource.setPassword("password");
	flyway.setDataSource(dataSource);
	flyway.setSchemas("test_university");
	flyway.clean();
	flyway.migrate();

    }

    @Test
    public void testAdd() throws Exception {
    }

    @Test
    public void testDelete() throws Exception {
	studentDao.delete(19);

    }
}
