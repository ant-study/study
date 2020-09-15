package kr.co.ant.study.imsoyeon.c_oop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestPaySample {
	
	private String type;
	
	private String productId;
	private String productName;
	private String amount;
	private String cardNo;
	private String cardCode;
	private String expireDate;
	private String mobileNo;
	private String userName;
	private String birthday;
	private String accountNo;
	private String bankCode;
	private String accountPw;
	
//	private CardInfo cardInfo;
//	private AccountInfo accountInfo;
//	private MobileInfo mobileInfo;
}
