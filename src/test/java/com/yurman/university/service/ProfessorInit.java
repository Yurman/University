package com.yurman.university.service;

import com.yurman.university.domain.Professor;

public class ProfessorInit {

    public static Professor getTestProfessor() {
        Professor professor = new Professor();
        professor.setFirstName("Ivan");
        professor.setLastName("Petrov");
        return professor;
    }
}
