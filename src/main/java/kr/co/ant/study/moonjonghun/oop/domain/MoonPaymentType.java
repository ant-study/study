package kr.co.ant.study.moonjonghun.oop.domain;

public enum MoonPaymentType {
	
	Card("CARD"),
	Phone("PHONE"),
	BankAccount("BANK");
	
	private String value;
	private MoonPaymentType (String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	} 
	
}
