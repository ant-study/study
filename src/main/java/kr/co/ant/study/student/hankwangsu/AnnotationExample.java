package kr.co.ant.study.student.hankwangsu;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import kr.co.ant.study.reflect.annotation.AnnotationQuestion;
import lombok.extern.slf4j.Slf4j;

/**
 * 문제가 있는 Class 상속
 * @author hankk
 *
 */
@Slf4j
public class AnnotationExample extends AnnotationQuestion{
	
	
	@Override
	public boolean validate(Object o) throws Exception {
		Class clazz = o.getClass();
		
		//Class에 선언된 Field 목록 조회 (LengthVO => [address, phone]) 
		Field[] fields = clazz.getDeclaredFields();
		
		for(Field field : fields) {
			//Field에 선언된 Annotation 목록 조회 (address => MaxLength, phone => MinLength)
			Annotation[] annotations = field.getAnnotations();
			for(Annotation anno : annotations ) {
				//각 Annotation별로 처리
			}
		}
		
		return false;
	}
	
	/**
	 * Test 할 수 있는 Main Class 생성
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		AnnotationExample e = new AnnotationExample();
		e.startTest();
		
	}

	
	

}
