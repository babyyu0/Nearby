package com.ssafy.trip.util.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MyException extends RuntimeException {
	private final HttpStatus status;
	public MyException(ErrorMessage errorMessage) {
		super(errorMessage.getMessage());
		this.status = errorMessage.getStatus();
	}
	public MyException(HttpStatus httpStatus, String message) {
		super(message);
		this.status = httpStatus;
	}
}
