package com.ssafy.trip.util.exception;

import org.springframework.http.HttpStatus;

public abstract class MyException extends Exception{
	abstract public HttpStatus getStatus();
	public MyException(String message) {
		super(message);
	}
}
