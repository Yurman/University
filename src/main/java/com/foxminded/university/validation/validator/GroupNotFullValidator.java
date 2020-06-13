package com.foxminded.university.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import com.foxminded.university.service.StudentService;
import com.foxminded.university.service.dto.StudentDto;
import com.foxminded.university.validation.annotation.GroupNotFull;

@PropertySource("application.properties")
public class GroupNotFullValidator implements ConstraintValidator<GroupNotFull, StudentDto> {

    @Autowired
    private StudentService studentService;

    @Value("${student.limit}")
    private int limit;

    @Override
    public boolean isValid(StudentDto student, ConstraintValidatorContext context) {

        return studentService.getAllStudentDtoByGroupId(student.getGroupId()).size() < limit;
    }

}
