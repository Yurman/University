package com.foxminded.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foxminded.university.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    public Student findById(int id);

    public List<Student> findAll();

    public Student save(Student student);

    public void deleteById(int id);

}
