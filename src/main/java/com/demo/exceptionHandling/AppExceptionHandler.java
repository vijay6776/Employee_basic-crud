package com.demo.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler(value=EmployeeException.class)
	public ResponseEntity<ExceptionBean> handleEmployeeException(EmployeeException ee){
		String message=ee.getMessage();
		ExceptionBean eb=new ExceptionBean();
		eb.setCode("ErrorCode");
		eb.setMsg(message);
		return new ResponseEntity<> (eb,HttpStatus.BAD_REQUEST);
		
	}
	

}
