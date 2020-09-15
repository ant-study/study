package kr.co.ant.study.oop.pg;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BankAccountInfo {
	private String accountNo;
	private String bankCode;
	private String accountPw;
}
