package com.foxminded.university.dao.impl.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Student;

@Repository("studentDaoHibernate")
@Primary
public class StudentDaoImpl implements StudentDao {

    private EntityManager entityManager = Persistence.createEntityManagerFactory("university").createEntityManager();

    @Override
    public Student getById(int id) {
        return entityManager.find(Student.class, id);

    }

    @Override
    public List<Student> getAll() {
        return entityManager.createQuery("select a from Student a", Student.class).getResultList();
    }

    @Override
    public Student add(Student student) {
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        return student;
    }

    @Override
    public boolean delete(int id) {
        Student student = entityManager.find(Student.class, id);
        entityManager.getTransaction().begin();
        entityManager.remove(student);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public Student update(Student student) {
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        return student;
    }

}
