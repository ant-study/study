package kr.co.ant.study.student.hankwangsu.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import kr.co.ant.study.reflect.annotation.LengthVO;
import kr.co.ant.study.reflect.annotation.MaxLength;
import kr.co.ant.study.reflect.annotation.MinLength;

public class Test {
	public static void main(String[] args) throws Exception {
		LengthVO a = new LengthVO();
		a.setAddress("1234");
		a.setPhoneNumber("123");
		
		Field[] fields = a.getClass().getDeclaredFields();
		Annotation[] as = a.getClass().getPackage().getAnnotations();
		for(Field field : fields) {
			Annotation[] anntations = field.getAnnotations();
			
			String getName = "get"+StringUtils.capitalize(field.getName());
			Method method = a.getClass().getMethod(getName);
			String value = (String) method.invoke(a);
			for(Annotation anno : anntations) {
				if(anno instanceof MaxLength) {
					int max = ((MaxLength)anno).value();
					if(value != null) {
						Assert.isTrue(max >= value.length(), String.format("%s Length = %s,  최대 길이 %s보다 큽니다.", field.getName(), value.length(), max));
					}
				}
				if(anno instanceof MinLength) {
					int min = ((MinLength)anno).value();
					if(value != null) {
						Assert.isTrue(min <= value.length(), String.format("%s Length = %s,  최소길이 %s 보다  작습니다.", field.getName(), value.length(), min));
					}
				}
			}
			
		}
	}
}
