package com.devru.coopeuch.rest.dto;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class RestError {
	
    private HttpStatus status;
    private String message;
    private List<String> errors;
    
	public RestError(HttpStatus status, String message, List<String> errors) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}
	
	public RestError(HttpStatus status, String message, String error) {
		super();
		this.status = status;
		this.message = message;
		this.errors = Arrays.asList(error);;
	}

	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the errors
	 */
	public List<String> getErrors() {
		return errors;
	}
    
	
    
    
}
