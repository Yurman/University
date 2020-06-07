package com.foxminded.university.service.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.foxminded.university.validation.UniqueTitle;

public class GroupDto {
    private int id;

    @UniqueTitle(message = "Title should be unique")
    @Size(min = 2, max = 10, message = "Should be in between 2 and 10 characters")
    private String title;

    @Min(value = 1, message = "Year is between 1 and 6")
    @Max(value = 6, message = "Year is between 1 and 6")
    private int year;

    private String departmentTitle;
    private int departmentId;
    private boolean deleted;

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDepartmentTitle() {
        return departmentTitle;
    }

    public void setDepartmentTitle(String departmentTitle) {
        this.departmentTitle = departmentTitle;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
