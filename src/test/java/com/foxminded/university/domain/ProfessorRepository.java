package com.foxminded.university.domain;

public class ProfessorRepository {

    public static Professor getTestProfessor() {
	Professor professor = new Professor();
	professor.setAge(57);
	professor.setFirstName("Ivan");
	professor.setLastName("Petrov");
	return professor;
    }
}
