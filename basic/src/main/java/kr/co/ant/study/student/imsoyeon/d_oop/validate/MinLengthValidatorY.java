package kr.co.ant.study.student.imsoyeon.d_oop.validate;

import kr.co.ant.study.student.imsoyeon.d_oop.exception.MinLengthValidatorExceptionY;

public class MinLengthValidatorY implements PGValidatorY {

	@Override
	public void validate(String value, int length) throws Exception {
		
		if (value.length() < length) {
			throw new MinLengthValidatorExceptionY("자리수 미만입니다.");
		}
		
	}

}
