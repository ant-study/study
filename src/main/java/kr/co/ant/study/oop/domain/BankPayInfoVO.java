package kr.co.ant.study.oop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BankPayInfoVO {
	private String accountNo;
	private String bankCode;
	private String accountPw;
}
