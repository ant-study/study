package kr.co.ant.study.reflect.annotation;

import kr.co.ant.study.imsoyeon.second.MaxLength;
import kr.co.ant.study.imsoyeon.second.MinLength;
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
