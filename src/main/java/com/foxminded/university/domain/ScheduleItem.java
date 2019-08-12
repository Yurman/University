package com.foxminded.university.domain;

import java.time.LocalDate;
import java.util.List;

public class ScheduleItem {
    private Lection lection;
    private Professor professor;
    private List<Group> groups;
    private LocalDate date;
    private Room classRoom;

    public Lection getLection() {
	return lection;
    }

    public void setLection(Lection lection) {
	this.lection = lection;
    }

    public Professor getProfessor() {
	return professor;
    }

    public void setProfessor(Professor professor) {
	this.professor = professor;
    }

    public List<Group> getGroups() {
	return groups;
    }

    public void setGroups(List<Group> groups) {
	this.groups = groups;
    }

    public LocalDate getDate() {
	return date;
    }

    public void setDate(LocalDate date) {
	this.date = date;
    }

    public Room getClassRoom() {
	return classRoom;
    }

    public void setClassRoom(Room classRoom) {
	this.classRoom = classRoom;
    }

    @Override
    public int hashCode() {
	final int prime = 43;
	int result = 23;
	result = prime * result + ((classRoom == null) ? 0 : classRoom.hashCode());
	result = prime * result + ((date == null) ? 0 : date.hashCode());
	result = prime * result + ((groups == null) ? 0 : groups.hashCode());
	result = prime * result + ((lection == null) ? 0 : lection.hashCode());
	result = prime * result + ((professor == null) ? 0 : professor.hashCode());
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
	ScheduleItem other = (ScheduleItem) obj;
	if (classRoom == null) {
	    if (other.classRoom != null)
		return false;
	} else if (!classRoom.equals(other.classRoom))
	    return false;
	if (date == null) {
	    if (other.date != null)
		return false;
	} else if (!date.equals(other.date))
	    return false;
	if (groups == null) {
	    if (other.groups != null)
		return false;
	} else if (!groups.equals(other.groups))
	    return false;
	if (lection == null) {
	    if (other.lection != null)
		return false;
	} else if (!lection.equals(other.lection))
	    return false;
	if (professor == null) {
	    if (other.professor != null)
		return false;
	} else if (!professor.equals(other.professor))
	    return false;
	return true;
    }
}
