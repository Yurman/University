package com.yurman.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yurman.university.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    public Student findById(int id);

    public List<Student> findAll();

    public List<Student> findAllByDeleted(boolean deleted);

    public List<Student> findAllByGroupId(int groupId);

}
