package kr.co.ant.study.imsoyeon.d_oop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
/**
 * 화면에서 넘어올 계좌 결제수단 데이터
 * */
public class AccountInfoVO {
	
	private String accountNo;
	private String bankCode;
	private String accountPw;
}
