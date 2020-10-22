package com.yurman.university.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yurman.university.domain.Department;
import com.yurman.university.repository.DepartmentRepository;
import com.yurman.university.service.DepartmentService;
import com.yurman.university.service.dto.DepartmentDto;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Department group) {
        return departmentRepository.save(group);
    }

    @Override
    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department getDepartmentById(int id) {
        return departmentRepository.findById(id);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public DepartmentDto getDepartmentDto(int id) {
        return toDto(departmentRepository.findById(id));
    }

    @Override
    public List<DepartmentDto> getAllDepartmentDto() {
        return toDto(departmentRepository.findAll());
    }

    @Override
    public List<DepartmentDto> getAllUndeletedDepartmentDto() {
        return toDto(departmentRepository.findAllByDeleted(false));
    }

    private List<DepartmentDto> toDto(List<Department> departments) {
        return departments.stream().map(department -> toDto(department)).collect(Collectors.toList());
    }

    private DepartmentDto toDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setTitle(department.getTitle());
        departmentDto.setDeleted(department.isDeleted());
        if (department.getFaculty() != null) {
            departmentDto.setFacultyTitle(department.getFaculty().getTitle());
        }
        return departmentDto;
    }
}
