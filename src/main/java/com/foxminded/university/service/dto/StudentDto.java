package com.foxminded.university.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class StudentDto {
    private int id;

    @Size(min = 2, max = 10)
    @NotBlank
    private String firstName;

    @Size(min = 2, max = 20)
    @NotBlank
    private String lastName;

    private String groupTitle;
    private int groupId;
    private boolean deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
