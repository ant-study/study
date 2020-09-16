package kr.co.ant.study.moonjonghun.oop.pg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardInfo implements MoonPayInfo{
	private String cardNo;
	private String cardCode;
	private String expireDate;
}
