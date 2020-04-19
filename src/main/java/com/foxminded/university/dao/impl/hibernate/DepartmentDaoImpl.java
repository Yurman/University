package com.foxminded.university.dao.impl.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        CriteriaQuery<Department> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Department.class);
        CriteriaQuery<Department> select = criteriaQuery.select(criteriaQuery.from(Department.class));
        TypedQuery<Department> typedQuery = entityManager.createQuery(select);

        return typedQuery.getResultList();
    }

    @Override
    @Transactional
    public Department add(Department department) {
        entityManager.persist(department);
        entityManager.flush();
        return department;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        CriteriaDelete<Department> criteriaDelete = entityManager.getCriteriaBuilder()
                .createCriteriaDelete(Department.class);
        Root<Department> root = criteriaDelete.from(Department.class);
        criteriaDelete.where(root.get("id").in(id));
        entityManager.createQuery(criteriaDelete).executeUpdate();
        return true;
    }

    @Override
    @Transactional
    public Department update(Department department) {
        department = entityManager.merge(department);
        entityManager.flush();
        return department;
    }

}
