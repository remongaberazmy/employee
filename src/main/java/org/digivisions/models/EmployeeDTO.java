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

	@NotBlank(message = "First name must not be empty")
	private String firstName;

	@NotBlank(message = "Second name must not be empty")
	private String lastName;

	@NotBlank(message = "Email must not be empty")
	@Email(message = "Invalid email")
	@EmailExistence(message = "Email not found")
	private String email;

	@NotBlank(message = "Department must not be empty")
	private String department;

	@NotNull(message = "Salary must not be empty")
	@Min(value = 0, message = "Salary must be greater than zero")
	private BigDecimal salary;
}
