package org.digivisions.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Nationalized;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter@Setter
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "EMPLOYEE")
public class EmployeeEntity extends AbstractEntity {

	@NotNull
	@Nationalized
	@Column(name = "FIRST_NAME")
	private String firstName;

	@NotNull
	@Nationalized
	@Column(name = "LAST_NAME")
	private String lastName;

	@NotNull
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "DEPARTMENT")
	private String department;

	@Column(name = "SALARY")
	private BigDecimal salary;
}
