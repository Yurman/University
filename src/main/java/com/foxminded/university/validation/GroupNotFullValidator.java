package com.foxminded.university.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.foxminded.university.service.StudentService;

public class GroupNotFullValidator implements ConstraintValidator<GroupNotFull, Integer> {

    @Autowired
    private StudentService studentService;
    private static final int LIMIT = 30;

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        Long counter = studentService.getAllStudentDto().stream().filter(student -> id.equals(student.getGroupId()))
                .count();

        return counter < LIMIT;
    }

}
