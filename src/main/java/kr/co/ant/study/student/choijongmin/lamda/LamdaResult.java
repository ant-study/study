package kr.co.ant.study.student.choijongmin.lamda;

import java.util.function.BiFunction;

import kr.co.ant.study.student.choijongmin.oop.validate.LamdaValidator;

public class LamdaResult implements LamdaValidator  {
	public boolean check(Integer a, Integer b) {
		BiFunction<Integer, Integer, Boolean> f = (i1, i2) -> i1 == i2;
		return f.apply(a, b);
	}
}
