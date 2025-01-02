package iuh.fit.dhktpm117ctt.group06.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.time.LocalTime;

public class CloseTimeValidator implements ConstraintValidator<CloseTimeConstraint, LocalTime> {

    @Override
    public void initialize(CloseTimeConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalTime value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        try {
            Object annotatedObject = context.unwrap(Object.class);
            Field openTimeField = annotatedObject.getClass().getDeclaredField("openTime");
            openTimeField.setAccessible(true);
            LocalTime openTime = (LocalTime) openTimeField.get(annotatedObject);
            return value.isAfter(openTime) && openTime!= null;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            //throw new RuntimeException(e);
            return false;
        }
    }

}
