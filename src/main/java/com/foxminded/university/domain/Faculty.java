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

    @Override
    public int hashCode() {
	final int prime = 71;
	int result = 7;
	result = prime * result + ((departments == null) ? 0 : departments.hashCode());
	result = prime * result + ((title == null) ? 0 : title.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Faculty other = (Faculty) obj;
	if (departments == null) {
	    if (other.departments != null)
		return false;
	} else if (!departments.equals(other.departments))
	    return false;
	if (title == null) {
	    if (other.title != null)
		return false;
	} else if (!title.equals(other.title))
	    return false;
	return true;
    }
}
