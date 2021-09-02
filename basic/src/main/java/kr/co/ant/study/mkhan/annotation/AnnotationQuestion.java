package kr.co.ant.study.mkhan.annotation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AnnotationQuestion {
	
	public abstract boolean validate(Object o)throws Exception;
	
	public void startTest() throws Exception {
		
		LengthVO vo = new LengthVO();
		vo.setAddress("111222333");
		vo.setPhoneNumber("22");
		
		try {
			if(validate(vo)) {
				System.out.println("성공");
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
}
