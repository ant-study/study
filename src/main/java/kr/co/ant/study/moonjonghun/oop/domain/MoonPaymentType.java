package kr.co.ant.study.moonjonghun.oop.domain;

import kr.co.ant.study.moonjonghun.oop.payment.BankPayment;
import kr.co.ant.study.moonjonghun.oop.payment.CardPayment;
import kr.co.ant.study.moonjonghun.oop.payment.MobilePayment;

public enum MoonPaymentType {
	
	Card("CARD",CardPayment.class),
	Mobile("MOBILE", MobilePayment.class),
	Bank("BANK", BankPayment.class);
	
	private String value;
	private Class clazz;
	private MoonPaymentType (String value, Class clazz) {
		this.value = value;
		this.clazz = clazz;
	}
	
	public String getValue() {
		return value;
	} 
	
	public Class getClazz() {
		return clazz;
	}
	
}
