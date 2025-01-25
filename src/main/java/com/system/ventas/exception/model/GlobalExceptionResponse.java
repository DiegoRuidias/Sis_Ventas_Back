package com.system.ventas.exception.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data 
@Builder
@AllArgsConstructor
@JsonPropertyOrder({"status", "messages"})
public class GlobalExceptionResponse {

	private int status;
	private Set<ErrorMessage> messages;
	
}
