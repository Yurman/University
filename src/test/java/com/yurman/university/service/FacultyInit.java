package com.yurman.university.service;

import com.yurman.university.domain.Faculty;

public class FacultyInit {
    public static Faculty getTestFaculty() {
        Faculty faculty = new Faculty();
        faculty.setTitle("Mathematics");
        return faculty;
    }
}
