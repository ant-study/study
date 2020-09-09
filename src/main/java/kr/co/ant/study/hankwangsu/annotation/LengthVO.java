package kr.co.ant.study.hankwangsu.annotation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LengthVO {
	
	@SimpleLength(base = 10, sign = LengthCheck.LESS)
	private String address;
	
	@SimpleLength(base = 10, sign = LengthCheck.MORE)
	private String phoneNumber;

}
