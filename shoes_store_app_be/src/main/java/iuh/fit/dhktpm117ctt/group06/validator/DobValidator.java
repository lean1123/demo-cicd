package iuh.fit.dhktpm117ctt.group06.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

public class DobValidator implements ConstraintValidator<DobConstraint, Date> {

    private int min;

    @Override
    public void initialize(DobConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return true;
        }
        long years = ChronoUnit.YEARS.between(value.toInstant(), new Date().toInstant());

        return years >= min;
    }
}
