package com.foxminded.university.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.dao.DepartmentDao;
import com.foxminded.university.domain.Department;
import com.foxminded.university.service.DepartmentService;
import com.foxminded.university.service.dto.DepartmentDto;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao;

    @Autowired
    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public Department addDepartment(Department department) {
        return departmentDao.add(department);
    }

    @Override
    public Department updateDepartment(Department group) {
        return departmentDao.update(group);
    }

    @Override
    public boolean deleteDepartment(int id) {
        return departmentDao.delete(id);
    }

    @Override
    public Department getDepartmentById(int id) {
        return departmentDao.getById(id);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentDao.getAll();
    }

    @Override
    public DepartmentDto getDepartmentDto(int id) {
        return convertToDepartmentDto(departmentDao.getById(id));
    }

    @Override
    public List<DepartmentDto> getAllDepartmentDto() {
        List<DepartmentDto> allDepartmentDto = new ArrayList<>();
        List<Department> departments = departmentDao.getAll();
        for (Department department : departments) {
            allDepartmentDto.add(convertToDepartmentDto(department));
        }
        return allDepartmentDto;
    }

    private DepartmentDto convertToDepartmentDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setTitle(department.getTitle());
        if (department.getFaculty() != null) {
            departmentDto.setFacultyTitle(department.getFaculty().getTitle());
        }
        return departmentDto;
    }
}
