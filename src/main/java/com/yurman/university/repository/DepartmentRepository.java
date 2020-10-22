package com.yurman.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yurman.university.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    public Department findById(int id);

    public List<Department> findAll();

    public List<Department> findAllByDeleted(boolean deleted);

}
