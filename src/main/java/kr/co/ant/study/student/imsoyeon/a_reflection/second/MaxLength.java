package kr.co.ant.study.student.imsoyeon.a_reflection.second;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * @interface
 * 		: annotation 선언
 * 		+
 * meta annotations 선언
 * 		= Custom Annotation
 * 
 * 뭐가 필요할까? 
 * 		언제, 어디에, 얼마나, 뭐를 등?
 * */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface MaxLength {

//	MaxLength : field의 max length를 정해두는 용도 → int
	int value();
//	int value() default 10;	: default 값이 할당되어 있으면, @MaxLength(10) 과 같이 annotation 적용 시 프로퍼티 값을 직접 정해줄 필요가 없다.
}
