package com.foxminded.university.dao;

import java.util.List;

import com.foxminded.university.domain.Student;

public interface StudentDao {
    public Student getById(int id);

    public List<Student> getStudentsByGroupId(int id);

    public int addStudent(int id, String firstName, String lastName, int group_id);

    public boolean deleteStudent(int id);

    public boolean updateStudent(int id, String firstName, String lastName, int group_id);

}
