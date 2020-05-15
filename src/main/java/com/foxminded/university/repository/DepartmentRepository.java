package com.foxminded.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foxminded.university.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    public Department findById(int id);

    public List<Department> findAll();

    public Department save(Department department);

    public void deleteById(int id);

}
