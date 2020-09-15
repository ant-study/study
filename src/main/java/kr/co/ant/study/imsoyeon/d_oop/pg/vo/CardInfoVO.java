package kr.co.ant.study.imsoyeon.d_oop.pg.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CardInfoVO {

	private String cardNo;
	private String cardCode;
	private String expireDate;
}
