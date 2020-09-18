package kr.co.ant.study.student.moonjonghun.oop.pg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoonMobilePaymentInfo {
	
	private String productId;
	private String productName;
	private int amount;
	private String paymentType;
	
	private MobileInfo mobileInfo;
	
}
