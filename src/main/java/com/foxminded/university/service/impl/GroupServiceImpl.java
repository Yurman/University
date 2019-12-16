package com.foxminded.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.dao.GroupDao;
import com.foxminded.university.domain.Group;
import com.foxminded.university.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Override
    public Group addGroup(Group group) {
        return groupDao.add(group);
    }

    @Override
    public Group updateGroup(Group group) {
        return groupDao.update(group);
    }

    @Override
    public boolean deleteGroup(int id) {
        return groupDao.delete(id);
    }

    @Override
    public Group getGroupById(int id) {
        return groupDao.getById(id);
    }

    @Override
    public List<Group> Groups() {
        return groupDao.getAll();
    }
}
