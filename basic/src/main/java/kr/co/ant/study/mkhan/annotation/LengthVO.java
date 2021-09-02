package kr.co.ant.study.mkhan.annotation;

import kr.co.ant.study.mkhan.annotation.LengthValidate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LengthVO {
	
	//Annotation MaxLength 생성
//	@MaxLength(maxValue = 10)
	@LengthValidate(maxValue = 10, code = "01")
	private String address;
	
	//Annotation MinLength 생성
//	@MinLength(minValue = 10)
	@LengthValidate(minValue = 10, code = "02")
	private String phoneNumber;

}
