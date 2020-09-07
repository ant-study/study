package kr.co.ant.study.reflect.annotation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LengthVO {
	//Annotation MaxLength 생성
	@MaxLength(10)
	private String address;
	
	//Annotation MinLength 생성
	@MinLength(10)
	private String phoneNumber;

}
