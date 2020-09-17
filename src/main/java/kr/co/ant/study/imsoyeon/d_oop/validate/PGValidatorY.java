package kr.co.ant.study.imsoyeon.d_oop.validate;

import kr.co.ant.study.imsoyeon.d_oop.domain.RequestPayInfo;

/**
 * validation 체크 유형
 * 		: 고정 / 최소 .. & 자릿수 ..
 * 고정이냐 최소냐 변경 가능. 자릿수 변경 가능
 * 
 * strategy pattern
 * */
public interface PGValidatorY {

	public void validate(String value, int length) throws Exception;
}
