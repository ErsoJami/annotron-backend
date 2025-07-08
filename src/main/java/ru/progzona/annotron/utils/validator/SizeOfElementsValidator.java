package ru.progzona.annotron.utils.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class SizeOfElementsValidator implements ConstraintValidator<SizeOfElements, List<String>> {

    private int min;
    private int max;

    @Override
    public void initialize(SizeOfElements constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(List<String> list, ConstraintValidatorContext context) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        for (String element : list) {
            if (element == null) {
                continue;
            }
            int length = element.length();
            if (length < min || length > max) {
                return false;
            }
        }
        return true;
    }
}