package kr.co.ant.study.student.imsoyeon.c_oop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CardInfo implements DetailsInfo {

	private String cardNo;
	private String cardCode;
	private String expireDate;
	
}
