package kr.co.ant.study.student.choijongmin;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MinLength {
	int value();
}
