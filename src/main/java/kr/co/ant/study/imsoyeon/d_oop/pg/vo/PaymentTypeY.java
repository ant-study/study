package kr.co.ant.study.imsoyeon.d_oop.pg.vo;

public enum PaymentTypeY {

	CARD("CARD"),
	ACCOUNT("ACCOUNT"),
	MOBILE("MOBILE");
	
	private String type;
	
	private PaymentTypeY(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
}
