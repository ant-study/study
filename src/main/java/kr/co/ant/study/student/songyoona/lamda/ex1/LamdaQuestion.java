package kr.co.ant.study.student.songyoona.lamda.ex1;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class LamdaQuestion {
	private static final String PREFIX = "my";

	public static void exe(Function<String, String> f) {
		List<String> list = Arrays.asList("name", "job", "address");
		for(String s : list) {
			System.out.println(f.apply(s));
		}
	}



	public static void main(String[] args) {
		//exe를 호출 해서 my_name, my_job, my_address 라고 출력되게 구현 해보세요

	    // f = PREFIX + "_" 가 합쳐진 함수
	    Function<String,String> words = s -> PREFIX + "_" + s;
	    exe(words);
	}


}
