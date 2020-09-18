package kr.co.ant.study.student.hankwangsu.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class SimpleTest {
	public static void main(String[] args) throws Exception {
		LengthVO a = new LengthVO();
		a.setAddress("123");
		a.setPhoneNumber("12342222222222225");
		
		Field[] fields = a.getClass().getDeclaredFields();
		for(Field field : fields) {
			SimpleLength simple = field.getAnnotation(SimpleLength.class);
			if(simple != null) {
				int base = simple.base();
				LengthCheck check = simple.sign();
				
				String getName = "get"+StringUtils.capitalize(field.getName());
				Method method = a.getClass().getMethod(getName);
				String value = (String) method.invoke(a);
				if(value != null) {
					if(!check.test(base, value.length())) {
						System.out.println(String.format("%s Length = %s,  길이가 %s보다 %s 이여야 합니다.,", field.getName(), value.length(), base, check.signName()));
					}
				}
			}
		}
	}
}
