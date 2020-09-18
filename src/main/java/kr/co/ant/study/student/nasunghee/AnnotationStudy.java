package kr.co.ant.study.student.nasunghee;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.util.StringUtils;

import kr.co.ant.study.reflect.annotation.AnnotationQuestion;

public class AnnotationStudy extends AnnotationQuestion {

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface MaxLength {
		int value() default 10;

	}
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface MinLength {
		int value();

	}

	@Override
	public boolean validate(Object o) throws Exception {
		boolean flag = false;
		Class clazz = o.getClass(); //AnnotationQuestion 내의 LengthVO.class;
		
		//LengthVO 안의 field를 불러오고 그 위 annotation을 불러와야함
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) { //field는 address, phoneNumber
			
			Method method = clazz.getDeclaredMethod("get"+ StringUtils.capitalize(field.getName())); //getAddress(), getPhoneNumber()
			
			MaxLength max = (MaxLength)field.getAnnotation(MaxLength.class);
			MinLength min = (MinLength)field.getAnnotation(MinLength.class);
			
			Object oj = method.invoke(o); //AnnotationQuestion에서 setting된 address, phoneNumber
			int val = oj.toString().length();
			
			if(max != null) {
				int maxValue = (int)max.value();
				if (val > maxValue) {
					System.out.println(field.getName() + "의 길이가 10자를 초과하였습니다.");
				}
				else {
					flag = true;
				}
			}
			if(min != null) { 
				int minValue = (int)min.value();
				if (val < minValue) {
					System.out.println(field.getName() + "의 길이가 10자 미만입니다.");
				}
				else {
					flag = true;
				}
			}
		}
		
		return flag;
	}

	
	public static void main(String[] args) throws Exception {
		
		AnnotationStudy e = new AnnotationStudy();
		e.startTest();
		
	}

}
