package kr.co.ant.study.student.choijongmin.lamda;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

public class LamdaQuestion {
private static final String PREFIX = "my";
private static final String len = "testString";

	public static void exe(Function<String, String> f, LamdaResult l) {
		List<String> list = Arrays.asList("name", "job", "address");
		for(String s : list) {
			System.out.println(f.apply(s));
		}
		
		System.out.println("validation check 10자리 확인 용 : " + l.check(len.length(), 10));
	}
	
	public static void main(String[] args) {
		LamdaResult l = new LamdaResult();
		//exe를 호출 해서 my_name, my_job, my_address 라고 출력되게 구현 해보세요 
		Function<String, String> f = i -> PREFIX + "_" + i;
		exe(f, l);
		
		//인자 두개 validator 만들기
		BiFunction<Integer, Integer, Boolean> f1 = (i1, i2) -> i1 == i2;
		BiPredicate<Integer, Integer> bi = (i1, i2) -> i1 == i2;
		
		if( f1.apply(len.length(), 10) ) {
			System.out.println(true);
		}
	}
}