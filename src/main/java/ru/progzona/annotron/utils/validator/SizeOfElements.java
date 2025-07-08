package ru.progzona.annotron.utils.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = SizeOfElementsValidator.class)
public @interface SizeOfElements {
    int min();
    int max();
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}