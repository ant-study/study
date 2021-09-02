package kr.co.ant.study.reflect.spring;

public enum DeliveryStatus {
	READY("배송 준비중"),
	DELIVERING("배송중"), 
	DELIVERED("배송완료");
	
	private String value;
	private DeliveryStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
