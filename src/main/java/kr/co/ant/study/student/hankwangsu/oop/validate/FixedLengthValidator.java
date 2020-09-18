package kr.co.ant.study.student.hankwangsu.oop.validate;

public class FixedLengthValidator implements ANTValidator{

	@Override
	public boolean validate(int length, int target) {
		return length == target;
	}

}
