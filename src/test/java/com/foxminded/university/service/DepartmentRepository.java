package com.foxminded.university.service;

import com.foxminded.university.domain.Department;
import com.foxminded.university.domain.Faculty;

public class DepartmentRepository {
    public static Department getTestDepartment() {
        Department department = new Department();
        department.setTitle("Statistics");
        Faculty faculty = FacultyRepository.getTestFaculty();
        faculty.setId(1);
        department.setFaculty(faculty);
        department.setId(1);
        return department;
    }
}
