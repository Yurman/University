package com.foxminded.university.domain;

import java.util.List;

public class Department {
    private String title;
    private List<Professor> professors;
    private List<Group> groups;

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public List<Professor> getProfessors() {
	return professors;
    }

    public void setProfessors(List<Professor> professors) {
	this.professors = professors;
    }

    public List<Group> getGroups() {
	return groups;
    }

    public void setGroups(List<Group> groups) {
	this.groups = groups;
    }

    @Override
    public int hashCode() {
	final int prime = 41;
	int result = 10;
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
	Department other = (Department) obj;
	if (title == null) {
	    if (other.title != null)
		return false;
	} else if (!title.equals(other.title))
	    return false;
	return true;
    }
}
