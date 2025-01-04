package org.digivisions.validators;

import org.digivisions.Annotations.EmailExistence;
import org.digivisions.Services.EmailValidationService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailExistenceValidator implements ConstraintValidator<EmailExistence, String> {

	@Autowired
	private EmailValidationService emailValidationService;

	@Override
	public void initialize(EmailExistence constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
		return emailValidationService.isValidEmail(email);
	}
}
