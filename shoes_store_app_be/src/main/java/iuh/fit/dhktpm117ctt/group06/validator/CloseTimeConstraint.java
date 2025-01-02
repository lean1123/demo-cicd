package iuh.fit.dhktpm117ctt.group06.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CloseTimeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CloseTimeConstraint {
    String message() default "Close time must be after open time";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
