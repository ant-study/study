package kr.co.ant.study.oop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentInfoVO {
	private String productId;
	private String productName;
	private int amount;
	private String paymentType;
	
	private CardPayInfoVO cardPayInfo;
	private MobilePayInfoVO mobilePayInfo;
	private BankPayInfoVO bankPayInfo;
	
}
