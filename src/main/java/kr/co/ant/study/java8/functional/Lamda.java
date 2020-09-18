package kr.co.ant.study.java8.functional;

import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 여기서는 람다식 예제를 살펴 본다
 * @author hankk
 *
 */
public class Lamda {
	
	public static int t = 20;
	
	public static void main(String[] args) {
		samplePredicateLamda();
	}
	
	
	public static void samplePredicateLamda() {
		//OLD interface의 미구현 메소드가 하나인 경우에도 구현 클래스를 만들던가 아래와 같이 생성과 동시에 Body를 만들어 줘야 했음 
		//interface의 미구현 메소드가 여러개인 경우에는 여전히 이 방식으로 만들어 줘야함 
		Predicate<Integer> old = new Predicate<Integer>() {

			@Override
			public boolean test(Integer i) {
				return i < t;
			}
			
		};
		
		//이제 interface의 미구현 메소드가 하나인 경우에는 Lamda식으로 쉼게??? 만들수 있음
		// 문법
		// 한줄인경우 return을 명시 하지 않아도 알아서 return
		// ()는 인자값을 정의 Predicate Funtion은 인자값이 하나이기 때문에 하나만
		// 인자값이 없는 경우 () -> blah~blah
		// 인자값이 여러개인 경우 (one, two, three) -> blah~blah~
		// 자세한건 java 람다로 검색 해서 확인
		
		//한줄로 로직이 완성되면 블록을 사용 안해도 됨 return값이 있으면 return을 명시하지않아도 결과값이 바로 리턴됨
		Predicate<Integer> less = (i) -> i < t;
		
		//여러줄인 경우 블록을 포함 시켜야 되고  return 값이 있으면 return 을 명시 해야 한다.(여러줄 기준 세미콜론 ; 한줄로 해도 세미콜론이 여러개 있으면 블록을 쳐야함)
		//인자가 하나라면 () 생략가능 (i) -> ....  ==> i ->
		Predicate<Integer> more = i -> {
				System.out.println("Inner More Predicate");
				return i > t;
		};
		
		System.out.println("Less :: "+inputFuntion(less));
		System.out.println("More :: "+inputFuntion(more));
		
		//메소드 자체를 넘기기
		//객체::메소드 
		Lamda bb= new Lamda();
		System.out.println("equals :: "+inputFuntion(bb::intEquals));
		
		//바로 사용
		System.out.println("not equals :: "+inputFuntion((i) -> i != t));
	}
	
	public static boolean inputFuntion(Predicate<Integer> p) {
		int i = 10;
		return p.test(i);
	}
	
	public boolean intEquals(Integer i) {
		int t = 20;
		return i == t;
	}
	
}
