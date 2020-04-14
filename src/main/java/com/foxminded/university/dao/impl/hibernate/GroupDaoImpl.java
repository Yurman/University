package com.foxminded.university.dao.impl.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.foxminded.university.dao.GroupDao;
import com.foxminded.university.domain.Group;

@Repository("groupDaoHibernate")
@Primary
public class GroupDaoImpl implements GroupDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(GroupDaoImpl.class);

    @Override
    public Group getById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Group> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Group add(Group t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Group update(Group t) {
        // TODO Auto-generated method stub
        return null;
    }

}
