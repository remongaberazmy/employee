package org.digivisions.exceptions;

public class InvalidInputException extends BaseException{
	public static final String ERROR_MSG = "EMPLOYEE.INVALID-INPUT.EXCEPTION";

	public InvalidInputException(){
		super(ERROR_MSG);
	}

	public InvalidInputException(String msg){
		super(msg);
	}
}
