package com.system.ventas.exception;

import com.system.ventas.exception.model.ErrorMessage;
import com.system.ventas.exception.model.GlobalExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.HashSet;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<GlobalExceptionResponse> badRequest(HttpServletRequest request, MethodArgumentNotValidException exception) {
		Set<ErrorMessage> fieldErrors = new HashSet<ErrorMessage>();
		exception.getBindingResult()
			.getFieldErrors()
			.forEach(item -> {
				fieldErrors.add(
						ErrorMessage.builder()
							.code(item.getField())
							.message(item.getDefaultMessage())
						.build()
				);
			});
		
		GlobalExceptionResponse response =
				GlobalExceptionResponse.builder()
					.status(HttpStatus.BAD_REQUEST.value())
					.messages(fieldErrors)
				.build();
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({HandlerMethodValidationException.class})
	public ResponseEntity<GlobalExceptionResponse> badRequest2(HttpServletRequest request, HandlerMethodValidationException exception) {
		Set<ErrorMessage> fieldErrors = new HashSet<ErrorMessage>();
		exception.getAllErrors().forEach(item -> {
			fieldErrors.add(
					ErrorMessage.builder()
						.message(item.getDefaultMessage())
					.build()
					);
		});
		
		GlobalExceptionResponse response =
				GlobalExceptionResponse.builder()
					.status(HttpStatus.BAD_REQUEST.value())
					.messages(fieldErrors)
				.build();
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({BusinessException.class})
	public ResponseEntity<GlobalExceptionResponse> badRequest3(HttpServletRequest request, BusinessException exception) {
		Set<ErrorMessage> fieldErrors = new HashSet<ErrorMessage>();
		for (String error : exception.getErrorMessages()) {
			fieldErrors.add(
					ErrorMessage.builder()
						.message(error)
					.build()
					);
		}
		
		GlobalExceptionResponse response =
				GlobalExceptionResponse.builder()
					.status(HttpStatus.BAD_REQUEST.value())
					.messages(fieldErrors)
				.build();
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
}
