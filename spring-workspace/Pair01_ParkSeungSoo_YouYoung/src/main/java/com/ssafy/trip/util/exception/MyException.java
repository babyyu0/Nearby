package com.ssafy.trip.util.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MyException extends Exception{
	private final HttpStatus status;
	public MyException(ErrorMessage errorMessage) {
		super(errorMessage.getMessage());
		this.status = errorMessage.getStatus();
	}
	public MyException(String message) {
		super(message);
		this.status = HttpStatus.INTERNAL_SERVER_ERROR;
	}
}
