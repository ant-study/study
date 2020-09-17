package kr.co.ant.study.imsoyeon.d_oop.validate;

import kr.co.ant.study.imsoyeon.d_oop.exception.FixLengthValidatorExceptionY;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FixLengthValidatiorY implements PGValidatorY {
	
	@Override
	public void validate(String value, int length) throws Exception {
		
		if (value.length() != length) {
			throw new FixLengthValidatorExceptionY("자리수가 맞지 않습니다.");
		}
	}

}
