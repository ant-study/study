package kr.co.ant.study.student.imsoyeon.f_functional.second;

import java.util.function.BiPredicate;

public class BiPredicateValidatorYImpl implements BIPredicateValidatorY {
	
	private BiPredicate<String, Integer> func;	

	public BiPredicateValidatorYImpl(BiPredicate<String, Integer> func) {
		super();
		this.func = func;
	}

	@Override
	public void validate(String value, int length) throws Exception {
		
//		BiPredicate 객체를 받아서 .test(value, length) 이거 하면 되지
		if (func.test(value, length)) {
			throw new Exception("유효성 검사를 충족하지 않습니다.");
		}	
		
	}

}
