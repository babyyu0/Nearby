package com.ssafy.trip.aop;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.trip.util.exception.MyException;

@RestControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(DataAccessException.class)
	public String handleException(DataAccessException e) {
		return "서버 처리 오류";
	}
	
	@ExceptionHandler(MyException.class)
	public String handleException(MyException e) {
		if(e.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR) return "서버 처리 오류";
		return e.getMessage();
	}

}