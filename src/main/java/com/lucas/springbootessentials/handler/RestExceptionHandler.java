package com.lucas.springbootessentials.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lucas.springbootessentials.exception.ResourceNotFoundDetails;
import com.lucas.springbootessentials.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
		return new ResponseEntity<>(new ResourceNotFoundDetails(
											"Resource not found",
											HttpStatus.NOT_FOUND.value(),
											resourceNotFoundException.getMessage(),
											new Date().getTime(),
											resourceNotFoundException.getClass().getName()
										),
									HttpStatus.NOT_FOUND);
	}
}
