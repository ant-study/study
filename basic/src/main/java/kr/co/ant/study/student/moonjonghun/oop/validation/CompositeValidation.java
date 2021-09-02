package kr.co.ant.study.student.moonjonghun.oop.validation;

import java.util.function.BiPredicate;

public class CompositeValidation implements Validation{
	
	protected BiPredicate<String, Integer> func;
	
	public CompositeValidation(BiPredicate<String, Integer> func) {
		this.func = func;
	};
	
	@Override
	public boolean validate(String s, int length) {
		return func.test(s, length);
	}
}
