package com.foxminded.university.dao;

import java.util.List;

import com.foxminded.university.domain.Department;

public interface DepartmentDao {
    public Department getDepartmentById(int id);

    public List<Department> getDepartmentByFacultyId(int id);

    public int addDepartment(int id, String title, int facultyId);

    public boolean deleteDepartment(int id);

    public boolean updateDepartment(int id);
}
