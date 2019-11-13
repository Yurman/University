package com.foxminded.university.dao.impl;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.foxminded.university.domain.Group;

public class GroupDaoImplTest {

    private GroupDaoImpl groupDao = new GroupDaoImpl();
    private TestDataInitializer testData = new TestDataInitializer();
    private Flyway flyway = TestDataInitializer.initializeFlyway();

    @Before
    public void setUp() throws Exception {
	flyway.clean();
	flyway.migrate();
	testData.initializeTestData();
    }

    @Test
    public void shouldGetGroupById() throws Exception {
	Group group = groupDao.getById(1);
	assertTrue(group != null);
	assertTrue(group.getTitle().equals("MP-11"));
    }

    @Test
    public void shouldUpdateGroup() throws Exception {
	Group group = testData.makeTestGroup();
	group.setId(1);
	group.setTitle("test");
	Group updatedGroup = groupDao.update(group);
	assertTrue(updatedGroup.getTitle().equals("test"));

    }

    @Test
    public void shouldGetAllGroups() throws Exception {
	List<Group> groups = groupDao.getAll();
	assertTrue(groups.size() == 2);
    }

    @Test
    public void shouldDeleteGroup() throws Exception {
	groupDao.delete(1);
	List<Group> groups = groupDao.getAll();
	assertTrue(groups.size() == 1);
    }

    @Test
    public void shouldAddGroup() throws Exception {
	Group group = testData.makeTestGroup();
	group.setTitle("test");
	Group addedGroup = groupDao.add(group);
	assertTrue(addedGroup.getId() == 3);
	assertTrue(groupDao.getById(3).getTitle().equals("test"));
	assertTrue(addedGroup.equals(groupDao.getById(3)));
    }

    @After
    public void tearDown() throws Exception {
	// flyway.clean();
    }

}
