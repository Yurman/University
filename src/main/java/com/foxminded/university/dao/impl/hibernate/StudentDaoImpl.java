package com.foxminded.university.dao.impl.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Student;

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
        return entityManager.createQuery("select a from Student a", Student.class).getResultList();
    }

    @Override
    @Transactional
    public Student add(Student student) {
        entityManager.persist(student);
        return student;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
        return true;
    }

    @Override
    @Transactional
    public Student update(Student student) {
        student = entityManager.merge(student);
        entityManager.persist(student);
        return student;
    }

}
