package com.foxminded.university.domain;

import java.time.LocalDate;

public class Lection {
    private String title;
    private LocalDate startTime;
    private LocalDate endTime;

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public LocalDate getStartTime() {
	return startTime;
    }

    public void setStartTime(LocalDate startTime) {
	this.startTime = startTime;
    }

    public LocalDate getEndTime() {
	return endTime;
    }

    public void setEndTime(LocalDate endTime) {
	this.endTime = endTime;
    }

    @Override
    public int hashCode() {
	final int prime = 81;
	int result = 8;
	result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
	result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
	Lection other = (Lection) obj;
	if (endTime == null) {
	    if (other.endTime != null)
		return false;
	} else if (!endTime.equals(other.endTime))
	    return false;
	if (startTime == null) {
	    if (other.startTime != null)
		return false;
	} else if (!startTime.equals(other.startTime))
	    return false;
	if (title == null) {
	    if (other.title != null)
		return false;
	} else if (!title.equals(other.title))
	    return false;
	return true;
    }
}
