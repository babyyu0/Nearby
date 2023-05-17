package com.ssafy.trip.aop;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.trip.util.MyException;

@RestControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(DataAccessException.class)
	public String handleException(DataAccessException e) {
		return "서버 처리 오류";
	}
	
	@ExceptionHandler(MyException.class)
	public String handleException(MyException e) {
		return e.getMessage();
	}

}