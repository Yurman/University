package com.yurman.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yurman.university.domain.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

    public Faculty findById(int id);

    public List<Faculty> findAll();

    public List<Faculty> findAllByDeleted(boolean deleted);

}
