package kr.co.ant.study.oop.domain;

public class PaySample {

	private String productId;
	
	private PaySubModule paySubModule;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public PaySubModule getPaySubModule() {
		return paySubModule;
	}

	public void setPaySubModule(PaySubModule paySubModule) {
		this.paySubModule = paySubModule;
	}
	
	
}
