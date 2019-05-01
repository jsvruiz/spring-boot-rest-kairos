package com.kairosds.practice.webservice.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.StaleObjectStateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * 
 * This class is used for to define the exceptions handlers globals for the
 * class PersonRest
 * 
 * @author jsvruiz
 * 
 */
@ControllerAdvice
public class PersonExceptionHandler {

	// add an exception handler for PersonErrorResponse
	@ExceptionHandler
	public ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException exception) {

		// create the response
		PersonErrorResponse errorResponse = new PersonErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<PersonErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	// add an exception handler for StaleObjectStateException
	@ExceptionHandler
	public ResponseEntity<PersonErrorResponse> handleException(StaleObjectStateException exception) {

		// create the response
		PersonErrorResponse errorResponse = new PersonErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<PersonErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	// add an exception handler for HttpMessageNotReadableException ( Person object
	// corrupted )
	@ExceptionHandler
	public ResponseEntity<PersonErrorResponse> handleException(HttpMessageNotReadableException exception) {

		// create the response
		PersonErrorResponse errorResponse = new PersonErrorResponse();

		String message = exception.getMessage();

		if (message.contains("java.time.LocalDate")) {
			message = "Remember that the birthdate field valid is: yyyy-mm-dd";
		} else {
			message = "You need send a Person valid object";
		}

		errorResponse.setMessage(message);
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<PersonErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	// add an exception handler for MethodArgumentNotValidException (validations in
	// Entity Person) )
	@ExceptionHandler
	public ResponseEntity<PersonErrorResponse> handleException(ConstraintViolationException exception) {

		// create the response
		PersonErrorResponse errorResponse = new PersonErrorResponse();
		Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
		List<String> messages = new ArrayList<>();

		for (ConstraintViolation<?> constraintViolation : constraintViolations) {
			messages.add(constraintViolation.getMessage());
		}

		errorResponse.setMessage("You need send a Person valid object: " + messages);
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<PersonErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	// add an exception handler for MethodArgumentNotValidException (validations in
	// Entity Person) )
	@ExceptionHandler
	public ResponseEntity<PersonErrorResponse> handleException(MethodArgumentNotValidException exception) {

		// create the response
		PersonErrorResponse errorResponse = new PersonErrorResponse();
		List<String> messages = new ArrayList<>();

		for (ObjectError error : exception.getBindingResult().getAllErrors()) {
			messages.add(error.getDefaultMessage());
		}

		errorResponse.setMessage("You need send a Person valid object: " + messages);
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<PersonErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	// add an exception handler for MethodArgumentTypeMismatchException for
	// parameters of input)
	@ExceptionHandler
	public ResponseEntity<PersonErrorResponse> handleException(MethodArgumentTypeMismatchException exception) {

		// create the response
		PersonErrorResponse errorResponse = new PersonErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<PersonErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

}
