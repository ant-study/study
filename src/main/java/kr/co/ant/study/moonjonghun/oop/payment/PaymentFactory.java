package kr.co.ant.study.moonjonghun.oop.payment;

import kr.co.ant.study.moonjonghun.oop.domain.MoonPaymentVO;
import kr.co.ant.study.moonjonghun.oop.domain.Payment;
import kr.co.ant.study.moonjonghun.oop.validation.FixedLengthValidation;
import kr.co.ant.study.moonjonghun.oop.validation.MinlengthValidation;

public class PaymentFactory {
	
	protected MoonPaymentVO data;
	protected String paymentType;
	
	public PaymentFactory(MoonPaymentVO vo, String type){
		this.data = vo;
		this.paymentType = type;
	}
	
	public Payment getPayment() throws Exception{
		if("CARD".equals(paymentType)) {
			return new CardPayment(data, new FixedLengthValidation());
		} else if("BANK".equals(paymentType)) {
			return new BankPayment(data, new FixedLengthValidation());
		} else if("MOBILE".equals(paymentType)) {
			return new MobilePayment(data, new MinlengthValidation());
		}else {
			throw new Exception("getPayment 중 오류 발생");
		}
	}
}
