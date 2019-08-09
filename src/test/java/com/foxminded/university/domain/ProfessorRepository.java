package com.foxminded.university.domain;

public class ProfessorRepository {
    private static Professor professor = new Professor();

    public static Professor getProfessor() {
        return professor;
    }

    public static void setProfessor(Professor professor) {
        ProfessorRepository.professor = professor;
    }
}
