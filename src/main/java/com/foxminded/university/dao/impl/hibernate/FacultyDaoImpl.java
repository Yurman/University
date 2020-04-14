package com.foxminded.university.dao.impl.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.foxminded.university.dao.FacultyDao;
import com.foxminded.university.domain.Faculty;

@Repository("facultyDaoHibernate")
@Primary
public class FacultyDaoImpl implements FacultyDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Faculty getById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Faculty> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Faculty add(Faculty t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Faculty update(Faculty t) {
        // TODO Auto-generated method stub
        return null;
    }

}
