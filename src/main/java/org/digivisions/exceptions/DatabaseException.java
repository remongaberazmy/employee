package org.digivisions.exceptions;

public class DatabaseException extends BaseException{
	public static final String ERROR_MSG = "EMPLOYEE.DATABASE.EXCEPTION";

	public DatabaseException(){
		super(ERROR_MSG);
	}

	public DatabaseException(String msg){
		super(msg);
	}
}
