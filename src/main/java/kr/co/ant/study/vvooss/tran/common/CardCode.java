package kr.co.ant.study.vvooss.tran.common;

public enum CardCode {

	KB_CARD("381"),
	SHINHAN_CARD("366"),
	HANA_CARD("044"),
	LOTTE_CARD("368"),
	BC_CARD("361"),
	NH_CARD("371"),
	SAMSUNG_CARD("365"),
	HYUNDAI_CARD("367");
	
	private String value;
	CardCode(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public String valueOf() {
		return value;
	}
}
