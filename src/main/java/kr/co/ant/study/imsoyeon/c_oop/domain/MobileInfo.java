package kr.co.ant.study.imsoyeon.c_oop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MobileInfo implements DetailsInfo {
	
	private String mobileNo;
	private String userName;
	private String birthDay;
}
