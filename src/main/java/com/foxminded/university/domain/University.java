package com.foxminded.university.domain;

import com.foxminded.university.dao.impl.StudentDaoImpl;
import com.foxminded.university.service.Schedule;

public class University {
    private String title;

    private Schedule schedule;

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public Schedule getSchedule() {
	return schedule;
    }

    public void setSchedule(Schedule schedule) {
	this.schedule = schedule;
    }

    public static void main(String[] args) {
	Student student = new Student();
	StudentDaoImpl studentDao = new StudentDaoImpl();
	student = studentDao.getById(14);
	System.out.println(student.getFirstName());
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
