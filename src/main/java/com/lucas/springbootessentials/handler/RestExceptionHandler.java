package com.lucas.springbootessentials.handler;

import com.lucas.springbootessentials.exception.ErrorDetail;
import com.lucas.springbootessentials.exception.ResourceNotFoundDetails;
import com.lucas.springbootessentials.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlerResourceNotFoundException(MethodArgumentNotValidException methodArgumentNotValidException){
		Map<String, String> errors = new HashMap<>();
		methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(new ErrorDetail(
				"Resource not found",
				HttpStatus.BAD_REQUEST.value(),
				errors.toString(),
				new Date().getTime(),
				methodArgumentNotValidException.getMessage()
		),
				HttpStatus.BAD_REQUEST);
	}
}
