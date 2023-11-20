package com.ssafy.trip.util.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MyException extends Exception{
	private final HttpStatus code;
	public MyException(ErrorMessage errorMessage) {
		super(errorMessage.getMessage());
		this.code = errorMessage.getStatus();
	}
}
