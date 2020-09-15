package kr.co.ant.study.oop.pg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ANTMobilePaymentInfo {
	
	private String productId;
	private String productName;
	private int amount;
	private String paymentType;
	
	private MobileInfo mobileInfo;
}
