package com.foxminded.university.dao;

import com.foxminded.university.domain.Group;

public interface GroupDao {
    public Group getGroupById(int id);

    public int addGroup(int id, int year, String title, int department_id);

    public boolean deleteGroup(int id);

    public boolean updateGroup(int id);

}
