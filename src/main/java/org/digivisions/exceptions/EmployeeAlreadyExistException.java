package org.digivisions.exceptions;

public class EmployeeAlreadyExistException extends BaseException{
	public static final String ERROR_MSG = "EMPLOYEE.ALREADY.EXIST.EXCEPTION";

	public EmployeeAlreadyExistException(){
		super(ERROR_MSG);
	}

	public EmployeeAlreadyExistException(String msg){
		super(msg);
	}
}
