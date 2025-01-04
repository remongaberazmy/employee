package org.digivisions.exceptions;

public class BaseException extends RuntimeException{

	private static final String ERROR_MESSAGE = "Exception Occurred";

	public BaseException(){
		super(ERROR_MESSAGE);
	}

	public BaseException(String msg){
		super(msg);
	}
}
