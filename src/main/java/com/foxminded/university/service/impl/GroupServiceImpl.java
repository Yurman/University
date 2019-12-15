package com.foxminded.university.service.impl;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.foxminded.university.config.DataConfiguration;
import com.foxminded.university.dao.impl.GroupDaoImpl;
import com.foxminded.university.domain.Group;
import com.foxminded.university.service.GroupService;

@Component
public class GroupServiceImpl implements GroupService {
    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
            DataConfiguration.class);
    private GroupDaoImpl groupDao = context.getBean(GroupDaoImpl.class);

    public Group addGroup(Group group) {
        return groupDao.add(group);
    }

    public Group updateGroup(Group group) {
        return groupDao.update(group);
    }

    public boolean deleteSGroup(Group group) {
        return groupDao.delete(group.getId());
    }

    public Group getStudentById(Group group) {
        return groupDao.getById(group.getId());
    }

    public List<Group> getAllGroups() {
        return groupDao.getAll();
    }
}
