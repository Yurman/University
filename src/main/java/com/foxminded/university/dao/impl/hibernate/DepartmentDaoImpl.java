package com.foxminded.university.dao.impl.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.foxminded.university.dao.DepartmentDao;
import com.foxminded.university.domain.Department;

@Repository("departmentDaoHibernate")
@Primary
public class DepartmentDaoImpl implements DepartmentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Department getById(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Department> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Department add(Department t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Department update(Department t) {
        // TODO Auto-generated method stub
        return null;
    }

}
