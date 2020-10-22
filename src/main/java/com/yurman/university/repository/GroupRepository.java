package com.yurman.university.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yurman.university.domain.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    public Group findById(int id);

    public List<Group> findAll();

    public List<Group> findAllByDeleted(boolean deleted);

}
