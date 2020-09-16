package kr.co.ant.study.songyoona.oop2.pg.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CardPayInfo implements ANTPayInfo{

	private String cardNo;
	private String cardCode;
	private String expireDate;

}
