package kr.co.ant.study.oop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CardPayInfoVO {

	private String cardNo;
	private String cardCode;
	private String expireDate;
	
}
