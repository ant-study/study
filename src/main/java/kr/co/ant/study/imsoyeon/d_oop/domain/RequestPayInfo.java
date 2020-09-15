package kr.co.ant.study.imsoyeon.d_oop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestPayInfo {

	private String type;
	
	private String productId;
	private String productName;
	private String amount;
	
	private CardInfoVO cardInfo;
	private AccountInfoVO accountInfo;
	private MobileInfoVO mobileInfo;
}
