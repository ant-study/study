package kr.co.ant.study.moonjonghun.oop.domain;

public class MoonPayDTO {
	
	//Request
	private String productId; 				//상품번호
	private String productName;				//상품명
	private int amount;						//수량
	private MoonPaymentType paymentType;	//결제타입
	private MoonCardInfo cardInfo;			//카드정보
	private MoonMobileInfo mobileInfo;		//휴대폰정보
	private MoonAccountInfo accountInfo;	//계좌정보
	
	//Response
	private Boolean success;				//결제결과
	private MoonReceipt receipt;			//영수증
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public MoonPaymentType getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(MoonPaymentType paymentType) {
		this.paymentType = paymentType;
	}
	public MoonCardInfo getCardInfo() {
		return cardInfo;
	}
	public void setCardInfo(MoonCardInfo cardInfo) {
		this.cardInfo = cardInfo;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public MoonReceipt getReceipt() {
		return receipt;
	}
	public void setReceipt(MoonReceipt receipt) {
		this.receipt = receipt;
	}
	public MoonMobileInfo getMobileInfo() {
		return mobileInfo;
	}
	public void setMobileInfo(MoonMobileInfo mobileInfo) {
		this.mobileInfo = mobileInfo;
	}
	public MoonAccountInfo getAccountInfo() {
		return accountInfo;
	}
	public void setAccountInfo(MoonAccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}
	
	
	
}
