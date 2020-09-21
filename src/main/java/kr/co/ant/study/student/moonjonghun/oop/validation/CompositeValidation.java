package kr.co.ant.study.student.moonjonghun.oop.validation;

import java.util.function.BiPredicate;

public class CompositeValidation implements Validation{
	
	protected String type;
	
	public CompositeValidation(String type) {
		this.type = type;
	};
	
	@Override
	public boolean validate(String s, int length) {
		BiPredicate<String, String> typeChk = (typ, data) -> typ.equals(data);
		if(typeChk.test(type, "MOBILE")) {
			return s.length() > length;
		} else {
			return s.length() == length;
		}
	}
}
