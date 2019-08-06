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
}