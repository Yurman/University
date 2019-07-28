package com.foxminded.university;

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
}