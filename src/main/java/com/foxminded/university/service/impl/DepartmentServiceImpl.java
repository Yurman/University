package com.foxminded.university.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foxminded.university.domain.Department;
import com.foxminded.university.repository.DepartmentRepository;
import com.foxminded.university.service.DepartmentService;
import com.foxminded.university.service.dto.DepartmentDto;

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
        return convertToDepartmentDto(departmentRepository.findById(id));
    }

    @Override
    public List<DepartmentDto> getAllDepartmentDto() {
        return convertToListDto(departmentRepository.findAll());
    }

    @Override
    public List<DepartmentDto> getAllUndeletedDepartmentDto() {
        return convertToListDto(departmentRepository.findAllByDeleted(false));
    }

    private List<DepartmentDto> convertToListDto(List<Department> departments) {
        return departments.stream().map(department -> convertToDepartmentDto(department)).collect(Collectors.toList());
    }

    private DepartmentDto convertToDepartmentDto(Department department) {
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
