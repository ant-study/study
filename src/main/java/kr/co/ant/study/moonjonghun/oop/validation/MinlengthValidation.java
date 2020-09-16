package kr.co.ant.study.moonjonghun.oop.validation;

public class MinlengthValidation implements Validation{
	
	@Override
	public boolean validate(String s, int minLength){
//		if(s.length() < minLength) {
//			throw new ValidateException(minLength + "자리보다 커야합니다.");
//		}
		return s.length() > minLength;
	}
}
