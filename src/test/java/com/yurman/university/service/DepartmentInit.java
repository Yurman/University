package com.yurman.university.service;

import com.yurman.university.domain.Department;

public class DepartmentInit {
    public static Department getTestDepartment() {
        Department department = new Department();
        department.setTitle("Statistics");

        return department;
    }
}
