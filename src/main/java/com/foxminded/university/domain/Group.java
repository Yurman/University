package com.foxminded.university.domain;

import java.util.List;

public class Group {
    private String title;
    private Department department;
    private String year;
    private List<Student> students;

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public Department getDepartment() {
	return department;
    }

    public void setDepartment(Department department) {
	this.department = department;
    }

    public String getYear() {
	return year;
    }

    public void setYear(String year) {
	this.year = year;
    }

    public List<Student> getStudents() {
	return students;
    }

    public void setStudents(List<Student> students) {
	this.students = students;
    }

    @Override
    public int hashCode() {
	final int prime = 51;
	int result = 15;
	result = prime * result + ((department == null) ? 0 : department.hashCode());
	result = prime * result + ((title == null) ? 0 : title.hashCode());
	result = prime * result + ((year == null) ? 0 : year.hashCode());
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
	Group other = (Group) obj;
	if (department == null) {
	    if (other.department != null)
		return false;
	} else if (!department.equals(other.department))
	    return false;
	if (title == null) {
	    if (other.title != null)
		return false;
	} else if (!title.equals(other.title))
	    return false;
	if (year == null) {
	    if (other.year != null)
		return false;
	} else if (!year.equals(other.year))
	    return false;
	return true;
    }
}
