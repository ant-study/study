package kr.co.ant.study.moonjonghun.oop.pg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MobileInfo implements MoonPayInfo{
	private String mobileNo;
	private String userName;
	private String brithDay;
}

