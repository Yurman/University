package com.foxminded.university.service;

import java.util.List;
import com.foxminded.university.domain.*;

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
}
