package kr.co.ant.study.student.moonjonghun.oop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MoonPaymentVO {
	
	//Request
	private String productId; 				//상품번호
	private String productName;				//상품명
	private int amount;						//결제금액
	private String paymentType;				//결제타입
	
	private MoonCardInfoVO cardPayInfo;			//카드정보
	private MoonMobileInfoVO mobilePayInfo;		//휴대폰정보
	private MoonAccountInfoVO accountPayInfo;	//계좌정보
	
	//Response
	private Boolean success;				//결제결과
	private MoonReceiptVO receipt;			//영수증
	
}
