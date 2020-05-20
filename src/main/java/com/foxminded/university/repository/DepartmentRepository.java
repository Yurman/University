package com.foxminded.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.foxminded.university.domain.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    public Department findById(int id);

    public List<Department> findAll();

    @Query("select d from Department d where deleted = false")
    public List<Department> findAllUndeleted();

}
