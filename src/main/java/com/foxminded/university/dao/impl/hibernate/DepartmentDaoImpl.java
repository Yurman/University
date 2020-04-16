package com.foxminded.university.dao.impl.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.foxminded.university.dao.DepartmentDao;
import com.foxminded.university.domain.Department;

@Repository("departmentDaoHibernate")
@Primary
public class DepartmentDaoImpl implements DepartmentDao {

    private EntityManager entityManager = Persistence.createEntityManagerFactory("university").createEntityManager();

    @Override
    public Department getById(int id) {
        return entityManager.find(Department.class, id);

    }

    @Override
    public List<Department> getAll() {
        return entityManager.createQuery("select a from Department a", Department.class).getResultList();
    }

    @Override
    public Department add(Department department) {
        entityManager.getTransaction().begin();
        entityManager.persist(department);
        entityManager.getTransaction().commit();
        return department;
    }

    @Override
    public boolean delete(int id) {
        Department department = entityManager.find(Department.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(department);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public Department update(Department department) {
        entityManager.getTransaction().begin();
        entityManager.persist(department);
        entityManager.getTransaction().commit();
        return department;
    }

}
