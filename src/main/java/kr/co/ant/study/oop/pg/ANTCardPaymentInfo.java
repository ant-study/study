package kr.co.ant.study.oop.pg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ANTCardPaymentInfo {
	
	private String productId;
	private String productName;
	private int amount;
	private String paymentType;
	
	private CardInfo cardInfo;
}
