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
}