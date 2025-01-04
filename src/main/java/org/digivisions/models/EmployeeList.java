package org.digivisions.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class EmployeeList extends BaseModel {
	private List<EmployeeDTO> employees;
}
