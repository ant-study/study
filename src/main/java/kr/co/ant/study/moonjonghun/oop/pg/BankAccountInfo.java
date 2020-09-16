package kr.co.ant.study.moonjonghun.oop.pg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankAccountInfo implements MoonPayInfo{
	private String accountNo;
	private String bankCode;
	private String accountPw;
}
