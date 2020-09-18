package kr.co.ant.study.student.moonjonghun;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FunctionalInterfaceTest {

//	static int c = 1;
//	static String d = "dd";
	
	public static void main(String[] args) {
		
		
		/** Type 1 : 선언과 동시에 구현*/
		int i1 = 10;
		String s1 = "쨔아아아알";
		//인터페이스를 구현체와 함께 작성하여 객체화한다. 매개변수명은 마음대로 지정
		FuncInterface dadad = (f, g) -> {return f+""+g;};

		// 객체화된 함수형 클래스에서 메소드를 꺼내쓴다.
		String result = dadad.fiMethod(i1, s1);
		System.out.println(result);
		
		
		
		/** Type 2 : 묻지도 따지지도 않고 변수명과 구현체만 넘겨서 
		 * 다른 메소드에서 함수형 인터페이스를 매개변수로 받음*/
		String rst = exec( (c , d) -> {
			//구현체
			Object e = c +""+d;
			String result2 = (String) e;
			System.out.println(result2);
			return result;
		});
		System.out.println("결과값 출력 ====> "+rst);
		
		/**java8 제공 함수형 인터페이스*/
		
		/**
		 * Runnable : 빈 매개함수, void 형식 thread를 수행하기 위한 목적의 함수형 인터페이스
		 * */
		Runnable r = () -> {
			for(int i = 0 ; i < 10 ; i++) {
				System.out.println(i);
			}
		};
		
		Thread thread = new Thread(r);
		thread.start();
		
		/** 
		 * Supplier<R> : 빈 매개함수 , 제네릭으로 입력받은 타입을 반환하는 형식으로 
		 * 입력값을 받지 않는 순수함수의 결과값만 받을때 사용한다.
		 * 주사위놀이를 할때 주사위를 굴린다는 행위만 있을뿐 결과값에 영향을 주지 않는다.
		 * */
		Supplier<String> sp = () -> 1+"";
		System.out.println("supplier test : "+sp.get());
		
		
		/**
		 * Function<T,R>  :  매개함수, 리턴값 을 반환하는 기본적인 함수의 형태
		 * */
		Function<Integer, double[]> func = double[]::new;
		Integer nF= 100;
		double[] dF= null;
		dF = func.apply(nF);
		
		
		/**
		 * Pridicate<T, Boolean>  :  매개함수를 받아 처리후 boolean을 반환하는 형태
		 * */
		
		Predicate<Integer> prid = (a) ->  a > 10;
		Boolean isTure = prid.test(nF);
		log.debug("is it ture?? : {}", isTure);
		
	}
	
	//함수형 인터페이스를 매개변수로 받는 함수 exec
	public static String exec(FuncInterface f) {
		
		int b = 28;
		String c = "쨜";
		String rst = f.fiMethod(b, c);
		return rst;
	}
}

