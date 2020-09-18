package kr.co.ant.study.student.moonjonghun.oop.validation;

public class FixedLengthValidation implements Validation{

	@Override
	public boolean validate(String s, int fixedLength){
//		if(s.length() != fixedLength) {
//			throw new ValidateException(fixedLength + "자리가 아닙니다.");
//		}
		return s.length() == fixedLength;
	}

}
