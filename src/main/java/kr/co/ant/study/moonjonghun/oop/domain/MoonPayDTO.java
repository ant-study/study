package kr.co.ant.study.moonjonghun.oop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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
	
}
