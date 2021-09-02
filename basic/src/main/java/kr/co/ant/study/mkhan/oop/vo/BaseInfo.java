package kr.co.ant.study.mkhan.oop.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BaseInfo {
	
	private String productId;
	
	private String productName;
	
	private long amount;
	
	private String paymentType;
	
	private CardInfo cardInfo;
	
	private MobileInfo mobileInfo;
	
	private BankInfo bankInfo;

}
