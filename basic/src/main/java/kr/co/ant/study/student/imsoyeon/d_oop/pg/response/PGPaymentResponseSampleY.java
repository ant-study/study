package kr.co.ant.study.student.imsoyeon.d_oop.pg.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * {	status : 200
 * 			body : {product_id : "P001", ...}
 * 				...
 * 		error :	없어} 
 * 
 *{
 * 	status : 400
 * 	error : {msg : "문제 있어" , field : "product_id", ... }
 *}
 * 
 * 
 * */
@Getter
@Setter
@ToString
public class PGPaymentResponseSampleY {
	
	private boolean success;

	private String statusCode;
	
	private ResponseBodyTestY body;
	
	private ResponseErrorTestY error;
}
