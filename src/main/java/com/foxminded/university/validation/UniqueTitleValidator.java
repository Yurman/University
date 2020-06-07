package com.foxminded.university.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.foxminded.university.service.GroupService;

public class UniqueTitleValidator implements ConstraintValidator<UniqueTitle, String> {

    @Autowired
    private GroupService groupService;

    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {

        Long counter = groupService.getAllGroupDto().stream().filter(group -> title.equals(group.getTitle())).count();

        return counter == 0;
    }

}
