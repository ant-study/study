package kr.co.ant.study.imsoyeon.d_oop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
/**
 * 화면에서 넘어올 결제수단 데이터
 * */
public class RequestPayInfo {

	private String type;
	
	private String productId;
	private String productName;
	private String amount;
	
	private CardInfoVO cardInfo;
	private AccountInfoVO accountInfo;
	private MobileInfoVO mobileInfo;
}
