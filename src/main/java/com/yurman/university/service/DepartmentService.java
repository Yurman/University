package com.yurman.university.service;

import java.util.List;

import com.yurman.university.domain.Department;
import com.yurman.university.service.dto.DepartmentDto;

public interface DepartmentService {

    public Department addDepartment(Department department);

    public Department updateDepartment(Department department);

    public Department getDepartmentById(int id);

    public void deleteDepartment(int id);

    public List<Department> getAllDepartments();

    public List<DepartmentDto> getAllDepartmentDto();

    public DepartmentDto getDepartmentDto(int id);

    List<DepartmentDto> getAllUndeletedDepartmentDto();

}
