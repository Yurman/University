package com.foxminded.university.dao.impl.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Student;
import com.foxminded.university.domain.Student_;

@Repository("studentDaoHibernate")
@Primary
public class StudentDaoImpl implements StudentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Student getById(int id) {
        return entityManager.find(Student.class, id);

    }

    @Override
    @Transactional
    public List<Student> getAll() {
        CriteriaQuery<Student> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Student.class);
        CriteriaQuery<Student> select = criteriaQuery.select(criteriaQuery.from(Student.class));
        TypedQuery<Student> typedQuery = entityManager.createQuery(select);

        return typedQuery.getResultList();
    }

    @Override
    @Transactional
    public Student add(Student student) {
        entityManager.persist(student);
        entityManager.flush();
        return student;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Student> criteriaDelete = criteriaBuilder.createCriteriaDelete(Student.class);
        Root<Student> root = criteriaDelete.from(Student.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get(Student_.id), id));
        entityManager.createQuery(criteriaDelete).executeUpdate();
        return true;
    }

    @Override
    @Transactional
    public Student update(Student student) {
        student = entityManager.merge(student);
        entityManager.flush();
        return student;
    }

}
