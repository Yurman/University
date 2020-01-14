package com.foxminded.university.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.dao.GroupDao;
import com.foxminded.university.domain.Group;
import com.foxminded.university.exception.EntityNotFoundException;
import com.foxminded.university.exception.QueryNotExecuteException;
import com.foxminded.university.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

    private GroupDao groupDao;
    private static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

    @Autowired
    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public Group addGroup(Group group) {
        logger.debug("addGroup() [{}]", group);
        try {
            groupDao.add(group);
        } catch (EntityNotFoundException ex) {
            logger.error("addGroup() [{}]", group, ex);
        } catch (QueryNotExecuteException exc) {
            logger.error("addGroup() [{}]", group, exc);
        }
        logger.trace("Result: [{}] ", group);
        return group;
    }

    @Override
    public Group updateGroup(Group group) {
        logger.debug("updateGroup() [{}]", group);
        try {
            groupDao.update(group);
        } catch (EntityNotFoundException ex) {
            logger.error("updateGroup() [{}]", group, ex);
        } catch (QueryNotExecuteException exc) {
            logger.error("updateGroup() [{}]", group, exc);
        }
        logger.trace("Result: [{}] ", group);
        return group;
    }

    @Override
    public boolean deleteGroup(int id) {
        logger.debug("deleteGroup() [{}]", id);
        boolean isDeleted = false;
        try {
            isDeleted = groupDao.delete(id);
        } catch (EntityNotFoundException ex) {
            logger.error("deleteGroup() " + id, ex);
        } catch (QueryNotExecuteException exc) {
            logger.error("deleteGroup() [{}]", id, exc);
        }
        logger.trace("Result: [{}] ", isDeleted);
        return isDeleted;
    }

    @Override
    public Group getGroupById(int id) {
        logger.debug("getGroupById() [{}]", id);
        Group group = null;
        try {
            group = groupDao.getById(id);
        } catch (EntityNotFoundException ex) {
            logger.error("getGroupById() [{}]", id, ex);
        } catch (QueryNotExecuteException exc) {
            logger.error("getGroupById() [{}]", id, exc);
        }
        logger.trace("Result: [{}] ", id);
        return group;
    }

    @Override
    public List<Group> getAllGroups() {
        logger.debug("getAllGroups()");
        List<Group> groups = null;
        try {
            groups = groupDao.getAll();
        } catch (EntityNotFoundException ex) {
            logger.error("getAllGroups()", ex);
        } catch (QueryNotExecuteException exc) {
            logger.error("getAllGroups()", exc);
        }
        logger.trace("Result: [{}] ", groups.size());
        return groups;
    }
}
