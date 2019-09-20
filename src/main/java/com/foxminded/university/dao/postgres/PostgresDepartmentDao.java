package com.foxminded.university.dao.postgres;

import java.util.List;

import com.foxminded.university.dao.DepartmentDao;
import com.foxminded.university.domain.Department;

public class PostgresDepartmentDao implements DepartmentDao {

    @Override
    public Department getDepartmentById(int id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Department> getDepartmentByFacultyId(int id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public int addDepartment(int id, String title, int facultyId) {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public boolean deleteDepartment(int id) {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean updateDepartment(int id) {
	// TODO Auto-generated method stub
	return false;
    }

}
