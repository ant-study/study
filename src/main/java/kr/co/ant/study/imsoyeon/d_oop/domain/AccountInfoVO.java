package kr.co.ant.study.imsoyeon.d_oop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountInfoVO {
	
	private String accountNo;
	private String bankCode;
	private String accountPw;
}
