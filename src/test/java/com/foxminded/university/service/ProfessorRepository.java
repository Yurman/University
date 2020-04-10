package com.foxminded.university.service;

import com.foxminded.university.domain.Professor;

public class ProfessorRepository {

    public static Professor getTestProfessor() {
        Professor professor = new Professor();
        professor.setFirstName("Ivan");
        professor.setLastName("Petrov");
        return professor;
    }
}
