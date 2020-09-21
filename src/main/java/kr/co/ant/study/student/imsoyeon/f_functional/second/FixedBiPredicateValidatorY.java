package kr.co.ant.study.student.imsoyeon.f_functional.second;

import java.util.function.BiPredicate;

import kr.co.ant.study.student.imsoyeon.d_oop.exception.FixLengthValidatorExceptionY;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FixedBiPredicateValidatorY implements BIPredicateValidatorY {
	
	@Override
	public void validate(String value, int length) throws Exception {
		
		BiPredicate<String, Integer> check = (v, l) -> v.length() != l;
		
		if (check.test(value, length)) {
			throw new FixLengthValidatorExceptionY("자리수가 맞지 않습니다.");
		}
	}	

}
