package com.foxminded.university.domain;

public class Student extends Person {
    private Group group;

    public Group getGroup() {
	return group;
    }

    public void setGroup(Group group) {
	this.group = group;
    }
}
