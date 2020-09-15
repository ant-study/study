/**
 * Author : jmChoi
 * Create Date : 2020. 9. 15.
 */
/**
 * @description : 
 * @author : jmChoi
 * @createDate : 2020. 9. 15.
 */
package kr.co.ant.study.choijongmin.oop.validate;

import kr.co.ant.study.oop.service.ValidateException;

public interface PaymentStrategy {
	public void validate() throws ValidateException;
	public abstract void paymentLogging();
}