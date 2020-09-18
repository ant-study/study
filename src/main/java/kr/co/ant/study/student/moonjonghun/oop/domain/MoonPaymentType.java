package kr.co.ant.study.student.moonjonghun.oop.domain;

import kr.co.ant.study.student.moonjonghun.oop.payment.BankPayment;
import kr.co.ant.study.student.moonjonghun.oop.payment.CardPayment;
import kr.co.ant.study.student.moonjonghun.oop.payment.MobilePayment;

public enum MoonPaymentType {
	
	Card("CARD",CardPayment.class),
	Mobile("MOBILE", MobilePayment.class),
	Bank("BANK", BankPayment.class);
	
	private String value;
	private Class<? extends Payment> clazz;
	private MoonPaymentType (String value, Class<? extends Payment> clazz) {
		this.value = value;
		this.clazz = clazz;
	}
	
	public String getValue() {
		return value;
	} 
	
	public Class<? extends Payment> getClazz() {
		return clazz;
	}
	
}
