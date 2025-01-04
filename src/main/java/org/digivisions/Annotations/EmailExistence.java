package org.digivisions.Annotations;

import org.digivisions.validators.EmailExistenceValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailExistenceValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailExistence {
	String message() default "EMAIL.EXIST";
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}
