package kr.co.ant.study.student.imsoyeon.f_functional.second;

import java.util.function.BiPredicate;

import kr.co.ant.study.student.imsoyeon.d_oop.exception.MinLengthValidatorExceptionY;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MinBIPredicateValidatorY implements BIPredicateValidatorY {
	
	@Override
	public void validate(String value, int length) throws Exception {
		
		BiPredicate<String, Integer> bp = (v, l) -> v.length() < l;
		
		if (bp.test(value, length)) {
			throw new MinLengthValidatorExceptionY("자리수 미만입니다");
		}
	}

}
