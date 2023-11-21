package com.ssafy.trip.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.trip.util.exception.MyException;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {
	
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<?> handleException(DataAccessException e) {
		log.error("DataAccessException : {}", e.getMessage());
		return ResponseEntity.internalServerError()
				.body("서버 오류입니다. 상황이 지속되면 문의 게시판을 이용해 주세요.");
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<?> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e) {
		log.error("methodArgumentNotValidException : {}", e.getFieldErrors().get(0).getDefaultMessage());
		return ResponseEntity.badRequest()
				.body(e.getFieldErrors().get(0).getDefaultMessage());
	}
	
	@ExceptionHandler(MyException.class)
	public ResponseEntity<?> handleException(MyException e) {
		log.error("MyException : {}", e.getMessage());
		String message = (e.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR)? "서버 오류입니다. 상황이 지속되면 문의 게시판을 이용해 주세요." : e.getMessage();
		return ResponseEntity.status(e.getStatus())
				.body(message);
	}

}