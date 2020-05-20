package com.foxminded.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.foxminded.university.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    public Student findById(int id);

    public List<Student> findAll();

    @Query("select s from Student s where deleted = false")
    public List<Student> findAllUndeleted();

}
