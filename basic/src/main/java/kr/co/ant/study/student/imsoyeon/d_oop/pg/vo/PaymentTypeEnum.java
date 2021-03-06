package kr.co.ant.study.student.imsoyeon.d_oop.pg.vo;

import kr.co.ant.study.student.imsoyeon.d_oop.payment.AccountPayment;
import kr.co.ant.study.student.imsoyeon.d_oop.payment.CardPayment;
import kr.co.ant.study.student.imsoyeon.d_oop.payment.MobilePayment;
import kr.co.ant.study.student.imsoyeon.d_oop.payment.Payment;
import kr.co.ant.study.student.imsoyeon.d_oop.validate.PGValidatorY;

public enum PaymentTypeEnum {

	CARD(CardPayment.class),
	ACCOUNT(AccountPayment.class),
	MOBILE(MobilePayment.class);
	
	private Class<? extends Payment> payment;
	
	private PaymentTypeEnum(Class<? extends Payment> payment) {
		this.payment = payment;
	}
	
	public Class<? extends Payment> getPayment() {
		return payment;
	}
}
