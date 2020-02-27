package com.ninepublishing.fairfax.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = DateValidator.class)
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidDate {
    String message() default "has unsupported date format. The valid date format is yyyy-MM-dd";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
