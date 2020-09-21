package kr.co.ant.study.student.imsoyeon.f_functional.second;

@FunctionalInterface
public interface BIPredicateValidatorY {
	
	public abstract void validate(String value, int length) throws Exception;
}
