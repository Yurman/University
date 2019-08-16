package com.foxminded.university.domain;

import java.util.List;
import com.foxminded.university.domain.Room;
import com.foxminded.university.service.Schedule;
import com.foxminded.university.domain.Faculty;

public class University {
    private String title;
    private List<Faculty> faculties;
    private List<Room> classRooms;
    private Schedule schedule;

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public List<Faculty> getFaculties() {
	return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
	this.faculties = faculties;
    }

    public List<Room> getClassRooms() {
	return classRooms;
    }

    public void setClassRooms(List<Room> classRooms) {
	this.classRooms = classRooms;
    }

    public Schedule getSchedule() {
	return schedule;
    }

    public void setSchedule(Schedule schedule) {
	this.schedule = schedule;
    }

    public static void main(String[] args) {

    }

    @Override
    public int hashCode() {
	final int prime = 123;
	int result = 37;
	result = prime * result + ((schedule == null) ? 0 : schedule.hashCode());
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
	University other = (University) obj;
	if (schedule == null) {
	    if (other.schedule != null)
		return false;
	} else if (!schedule.equals(other.schedule))
	    return false;
	if (title == null) {
	    if (other.title != null)
		return false;
	} else if (!title.equals(other.title))
	    return false;
	return true;
    }
}
