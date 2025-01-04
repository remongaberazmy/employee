package org.digivisions.controllers;

import org.digivisions.exceptions.DatabaseException;
import org.digivisions.exceptions.EmployeeAlreadyExistException;
import org.digivisions.exceptions.EmployeeNotFoundException;
import org.digivisions.exceptions.InvalidInputException;
import org.digivisions.models.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsController {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ErrorModel> handleEmployeeNotFoundException(EmployeeNotFoundException ex){
		ErrorModel errorModel = new ErrorModel();
		errorModel.addErrorMessage(EmployeeNotFoundException.ERROR_MSG);
		return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmployeeAlreadyExistException.class)
	public ResponseEntity<ErrorModel> handleEmployeeAlreadyExistException(EmployeeAlreadyExistException ex){
		ErrorModel errorModel = new ErrorModel();
		errorModel.addErrorMessage(EmployeeAlreadyExistException.ERROR_MSG);
		return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<ErrorModel> handleDatabaseException(DatabaseException ex){
		ErrorModel errorModel = new ErrorModel();
		errorModel.addErrorMessage(DatabaseException.ERROR_MSG);
		return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<ErrorModel> handleInvalidInputException(InvalidInputException ex){
		ErrorModel errorModel = new ErrorModel();
		errorModel.addErrorMessage(InvalidInputException.ERROR_MSG);
		return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorModel> handleValidationErrors(MethodArgumentNotValidException ex) {
		ErrorModel errorModel = new ErrorModel();
		ex.getBindingResult().getFieldErrors().forEach(error ->
				errorModel.addErrorMessage(error.getDefaultMessage())
		);
		return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
	}



	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorModel> handleException(Exception ex){
		ErrorModel errorModel = new ErrorModel();
		errorModel.addErrorMessage(ex.getMessage());
		return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
	}

}
