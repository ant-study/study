package kr.co.ant.study.reflect.annotation;

public abstract class AnnotationQuestion {
	
	public abstract boolean validate(Object o)throws Exception;
	
	
	public void startTest() throws Exception {
		//
		LengthVO vo = new LengthVO();
		vo.setAddress("최대10자오류");
		vo.setPhoneNumber("최소자리오류");
		validate(vo);
	}
	
}
