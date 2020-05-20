package com.foxminded.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.foxminded.university.domain.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

    public Faculty findById(int id);

    public List<Faculty> findAll();

    @Query("select f from Faculty f where deleted = false")
    public List<Faculty> findAllUndeleted();

}
