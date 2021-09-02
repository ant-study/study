package kr.co.ant.study.student.hankwangsu.oop.validate;

import java.util.function.BiPredicate;

public class FuntionalValidator implements ANTValidator{

	private BiPredicate<Integer, Integer> validator;
	
	public FuntionalValidator(BiPredicate<Integer, Integer> validator) {
		this.validator = validator;
	}
	
	@Override
	public boolean validate(int length, int target) {
		return validator.test(length, target);
	}

}
