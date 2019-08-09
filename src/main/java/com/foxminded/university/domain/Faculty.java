package com.foxminded.university.domain;

import java.util.List;

public class Faculty {
    private String title;
    private List<Department> departments;

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public List<Department> getDepartments() {
	return departments;
    }

    public void setDepartments(List<Department> departments) {
	this.departments = departments;
    }
}
