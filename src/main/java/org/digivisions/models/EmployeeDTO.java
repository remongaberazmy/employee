package org.digivisions.models;

import lombok.Getter;
import lombok.Setter;
import org.digivisions.Annotations.EmailExistence;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter@Setter
public class EmployeeDTO extends BaseModel {
	private Long id;

	@NotBlank(message = "FIRST_NAME_MUST_NOT_BE_EMPTY")
	private String firstName;

	@NotBlank(message = "SECOND_NAME_MUST_NOT_BE_EMPTY")
	private String lastName;

	@NotBlank(message = "EMAIL_NAME_MUST_NOT_BE_EMPTY")
	@Email(message = "INVALID_EMAIL")
	@EmailExistence(message = "EMAIL_NOT_EXIST")
	private String email;

	@NotBlank(message = "DEPARTMENT_NAME_MUST_NOT_BE_EMPTY")
	private String department;

	@NotNull(message = "SALARY_NAME_MUST_NOT_BE_EMPTY")
	@Min(value = 0, message = "SALARY_MUST_BE_GREATER_THAN_ZERO")
	private BigDecimal salary;
}
