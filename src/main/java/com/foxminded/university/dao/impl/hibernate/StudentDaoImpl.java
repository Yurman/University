package com.foxminded.university.dao.impl.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Student;

@Repository("studentDaoHibernate")
@Primary
public class StudentDaoImpl implements StudentDao {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("university");
    EntityManager entityManager = emf.createEntityManager();

    @Override
    public Student getById(int id) {
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, id);
        entityManager.detach(student);
        return student;
    }

    @Override
    public List<Student> getAll() {
        entityManager.getTransaction().begin();
        List<Student> students = null;
        entityManager.getTransaction().commit();
        return students;
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
        entityManager.getTransaction().begin();
        Student student = entityManager.find(Student.class, id);
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
