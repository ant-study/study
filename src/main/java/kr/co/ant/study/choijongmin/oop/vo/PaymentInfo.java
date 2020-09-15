package kr.co.ant.study.choijongmin.oop.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentInfo {
	
	private String productId;
	private String productName;
	private Long amount;
	private String paymentType;
	
	public InCardInfo inCardInfo;
	public InBankAccountInfo inBankAccountInfo;
	public InMobileInfo inMobileInfo;
}