package kr.co.ant.study.mkhan.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LengthValidate {
	
	int maxValue() default 10;
	
	int minValue() default 10;
	
	String code();

}
