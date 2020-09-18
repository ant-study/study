/**
 * Author : jmChoi
 * Create Date : 2020. 9. 15.
 */
package kr.co.ant.study.student.choijongmin.oop.validate;

import kr.co.ant.study.student.choijongmin.oop.vo.InBankAccountInfo;
import kr.co.ant.study.student.choijongmin.oop.vo.InCardInfo;
import kr.co.ant.study.student.choijongmin.oop.vo.InMobileInfo;

/**
 * @description : 
 * @author : jmChoi
 * @createDate : 2020. 9. 15.
 */
public enum PaymentType {
	CARD("CARD", InCardInfo.class),
	BANK("BANK", InBankAccountInfo.class),
	MOBILE("MOBILE", InMobileInfo.class);
	
	private String name;
	private Class<?> clazz;
	
	private <T extends Object> PaymentType(String value, Class<T> clazz) {
		this.name = value;
		this.clazz = clazz;
	}
	
	public String getName() {
		return name;
	}
	
	public Class<?> getPaymentClass() {
		return clazz;
	}

}
