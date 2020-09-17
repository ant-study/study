package kr.co.ant.study.imsoyeon.d_oop.pg.vo;

import kr.co.ant.study.imsoyeon.d_oop.payment.AccountPayment;
import kr.co.ant.study.imsoyeon.d_oop.payment.CardPayment;
import kr.co.ant.study.imsoyeon.d_oop.payment.MobilePayment;
import kr.co.ant.study.imsoyeon.d_oop.payment.Payment;
import kr.co.ant.study.imsoyeon.d_oop.validate.PGValidatorY;

public enum PaymentTypeEnum {

	CARD(CardPayment.class),
	ACCOUNT(AccountPayment.class),
	MOBILE(MobilePayment.class);
	
	private Class<?> payment;
	
	private PaymentTypeEnum(Class<?> payment) {
		this.payment = payment;
	}
	
	public Class<?> getPayment() {
		return payment;
	}
}
