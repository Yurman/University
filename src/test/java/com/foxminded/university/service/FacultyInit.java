package com.foxminded.university.service;

import com.foxminded.university.domain.Faculty;

public class FacultyInit {
    public static Faculty getTestFaculty() {
        Faculty faculty = new Faculty();
        faculty.setTitle("Mathematics");
        return faculty;
    }
}
