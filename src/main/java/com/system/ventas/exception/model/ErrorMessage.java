package com.system.ventas.exception.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonPropertyOrder({"code", "message"})
public class ErrorMessage {

	private String code;
	private String message;
	
}
