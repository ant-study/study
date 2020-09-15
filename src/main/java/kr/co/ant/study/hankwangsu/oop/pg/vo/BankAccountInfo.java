package kr.co.ant.study.hankwangsu.oop.pg.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BankAccountInfo implements ANTPayInfo {
	private String accountNo;
	private String bankCode;
	private String accountPw;
}
