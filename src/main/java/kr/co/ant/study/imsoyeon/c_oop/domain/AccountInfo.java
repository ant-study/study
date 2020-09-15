package kr.co.ant.study.imsoyeon.c_oop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountInfo implements DetailsInfo {
	
	private String accoutNo;
	private String bankCode;
	private String accountPw;
}
