package com.foxminded.university.dao.impl.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
    @Transactional
    public Department getById(int id) {
        return entityManager.find(Department.class, id);
    }

    @Override
    @Transactional
    public List<Department> getAll() {
        return entityManager.createQuery("select a from Department a", Department.class).getResultList();
    }

    @Override
    @Transactional
    public Department add(Department department) {
        entityManager.persist(department);
        return department;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        Department department = entityManager.find(Department.class, id);
        entityManager.remove(department);
        return true;
    }

    @Override
    @Transactional
    public Department update(Department department) {
        department = entityManager.merge(department);
        entityManager.persist(department);
        return department;
    }

}
