package kr.co.ant.study.moonjonghun.oop.domain;

public enum MoonPaymentType {
	
	Card("CARD"),
	Mobile("Mobile"),
	Bank("BANK");
	
	private String value;
	private MoonPaymentType (String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	} 
	
}
