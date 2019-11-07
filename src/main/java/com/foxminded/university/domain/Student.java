package com.foxminded.university.domain;

public class Student extends Person {

    private int id;
    private Group group;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public Group getGroup() {
	return group;
    }

    public void setGroup(Group group) {
	this.group = group;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((group == null) ? 0 : group.hashCode());
	result = prime * result + id;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Student other = (Student) obj;
	if (group == null) {
	    if (other.group != null) {
		return false;
	    }
	} else if (!group.equals(other.group))
	    return false;
	if (id != other.id)
	    return false;
	return true;
    }
}
