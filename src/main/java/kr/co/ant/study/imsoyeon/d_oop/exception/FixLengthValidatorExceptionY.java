package kr.co.ant.study.imsoyeon.d_oop.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FixLengthValidatorExceptionY extends Exception {	

	public FixLengthValidatorExceptionY(String message) {
		super(message);
	}	
}
