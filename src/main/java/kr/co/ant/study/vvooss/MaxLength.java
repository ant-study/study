package kr.co.ant.study.vvooss;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MaxLength {
	//String msg() default "sorry , bigger than maximum size";
	int value();
}
