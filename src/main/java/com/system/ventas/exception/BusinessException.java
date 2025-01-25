package com.system.ventas.exception;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Set<String> errorMessages;
	
	public BusinessException(String errorMessage) {
		errorMessages = new HashSet<String>();
		errorMessages.add(errorMessage);
	}

	public BusinessException(Set<String> errorMessages) {
		super();
		this.errorMessages = errorMessages;
	}
	
}
