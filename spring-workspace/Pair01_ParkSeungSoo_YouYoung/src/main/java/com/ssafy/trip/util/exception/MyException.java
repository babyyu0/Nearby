package com.ssafy.trip.util.exception;

import org.springframework.http.HttpStatus;

public class MyException extends Exception{
	private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
	public MyException(String message) {
		super(message);
	}
	public HttpStatus getStatus() {
		return status;
	};
}
