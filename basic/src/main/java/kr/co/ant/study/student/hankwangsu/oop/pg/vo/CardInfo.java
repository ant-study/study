package kr.co.ant.study.student.hankwangsu.oop.pg.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardInfo implements ANTPayInfo{
	private String cardNo;
	private String cardCode;
	private String expireDate;
}
