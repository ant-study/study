package kr.co.ant.study.student.songyoona.oop2.pg.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MobilePayInfo implements ANTPayInfo{
	private String mobileNo;
	private String userName;
	private String brithDay;
}
