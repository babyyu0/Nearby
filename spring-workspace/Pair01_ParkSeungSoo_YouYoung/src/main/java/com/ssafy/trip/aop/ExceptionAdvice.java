package com.ssafy.trip.aop;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.trip.util.exception.MyException;

@RestControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<?> handleException(DataAccessException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MyException.class)
	public ResponseEntity<?> handleException(MyException e) {
		return new ResponseEntity<>(e.getMessage(), e.getStatus());
	}

}