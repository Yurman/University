package com.foxminded.university.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueTitleValidator.class)
public @interface UniqueTitle {
    String message() default "{value.negative}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
