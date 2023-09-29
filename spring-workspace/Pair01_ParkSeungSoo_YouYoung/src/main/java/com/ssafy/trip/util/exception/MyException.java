package com.ssafy.trip.util.exception;

import org.springframework.http.HttpStatus;

public abstract class MyException extends Exception{
	public MyException(String message) {
		super(message);
	}
	abstract public HttpStatus getStatus();
}
