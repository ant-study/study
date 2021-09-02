package kr.co.ant.study.student.moonjonghun.functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class MoonLamdaQuestion {

	private static final String PREFIX = "my";
	
	public static void exe(Function<String, String> f) {
		List<String> list = Arrays.asList("name", "job", "address");
		for(String s : list) {
			System.out.println(f.apply(s));
		}
	}
	
	public static void main(String[] args) {
		//exe를 호출 해서 my_name, my_job, my_address 라고 출력되게 구현 해보세요
		
		//람다식 Function 함수 정의하기 
		// Function<리턴타입, 파라미터타입> (객체명) = 
		// 		(파라미터변수명)<파라미터타입> -> {(구현체 ...) return (리턴값)<리턴타입>};  
		Function<String, String> ff = s ->{
			return MoonLamdaQuestion.PREFIX + "_" + s; 
		};
		
		//함수호출
		exe(ff);
	}
}
