package kr.co.ant.study.student.imsoyeon.a_reflection.second;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MinLength {

//	MinLength : field의 min length 제한하는 용도 → int
	int value();
//	int value() default 10;
}
