package org.digivisions.exceptions;

public class EmployeeNotFoundException extends BaseException{

	public static final String ERROR_MSG = "EMPLOYEE.NOT-FOUND.EXCEPTION";

	public EmployeeNotFoundException(){
		super(ERROR_MSG);
	}

	public EmployeeNotFoundException(String msg){
		super(msg);
	}
}
