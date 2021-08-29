package de.c24.finacc.klt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Error detailed information for web service return with error
@JsonIgnoreProperties(ignoreUnknown = true)
public class Error {

	private String code;
	private String message;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
