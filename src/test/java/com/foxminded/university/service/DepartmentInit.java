package com.foxminded.university.service;

import com.foxminded.university.domain.Department;

public class DepartmentInit {
    public static Department getTestDepartment() {
        Department department = new Department();
        department.setTitle("Statistics");

        return department;
    }
}
