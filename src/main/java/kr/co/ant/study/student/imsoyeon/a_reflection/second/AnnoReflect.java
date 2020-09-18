package kr.co.ant.study.student.imsoyeon.a_reflection.second;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.util.StringUtils;

import kr.co.ant.study.reflect.annotation.AnnotationQuestion;
import lombok.extern.slf4j.Slf4j;

/*
 * reflect 2.
 * custom annotation 생성 후 validation 수행해보기
 * 
 * 어노테이션의 본질적인 목적은 소스코드에 메타데이터를 표현하는 것?
 * 단순히 부가적인 표현뿐만 아니라, reflection을 이용하면
 * annotation 지정만으로도 원하는 클래스를 주입하는 등의 것이 가능하대.
 * 
 * Built-in annotation & Meta annotation
 * 		• Built-in annotation : 기존에 알던거
 * 		• Meta annotation → 이걸로 custom annotation 만들어낼 수 있다.
 * */
@Slf4j
public class AnnoReflect extends AnnotationQuestion {

	@Override
	public boolean validate(Object o) throws Exception {
		
//		Class info
		Class clazz = o.getClass();
		
//		Fields info
		Field[] fields = clazz.getDeclaredFields();
		
		for (Field field : fields) {	
			
//			field value
			Method method = clazz.getMethod("get"+StringUtils.capitalize(field.getName()));
			String fieldValue = (String) method.invoke(o);
			
//			annotations info
			Annotation[] annotations =  field.getDeclaredAnnotations();
			for (Annotation anno : annotations) {
				
//				annotation type
//					compare field value with anno value
				if (anno instanceof MaxLength) {			
					MaxLength max = (MaxLength) anno;
					if (fieldValue.length() > max.value()) {
						throw new Exception(field.getName()+" : MaxLength error");
					}
				} else if (anno instanceof MinLength) {
					MinLength min = (MinLength) anno;
					if (fieldValue.length() < min.value()) {
						throw new Exception(field.getName()+" : MinLength error");
					}
				}
			}
			
			/*
			 * anno.annotationType() == MaxLength.class
			 * field.isAnnotationPresent(MaxLength.class)
			 */
		}
		
		
		return false;
	}
	
	public static void main (String args[]) throws Exception {
		AnnoReflect e = new AnnoReflect();
//		startTest() 호출 시 validation 체크
		e.startTest();
	}

}
