package kr.co.ant.study.java8.stream;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMapAndFlatMapAndReduce {

	public static void main(String[] args) {
		testReduce();
		testMap();
		testFlatMap();
	}
	
	/**
	 * 원본 Stream의 값에 대한 변환이 필요 할때 사용
	 */
	private static void testMap() {
		Stream<String> s = Stream.of("a", "b", "c", "d");
		
		//문자열뒤에 -1 붙이기
		List<String> li = s.map(al -> al+"-1").collect(Collectors.toList());
		li.forEach(System.out::println);
		
		//문자열의 길이로 변환
		Stream<String> s1 = Stream.of("a1", "b12", "c123", "d1234");
		List<Integer> li2 = s1.map(al -> al.length()).collect(Collectors.toList());
		li.forEach(System.out::println);
	}
	
	/**
	 * Flat 사전적 의미 : 평지, 평평한 
	 * 다차원 배열을 1차원 배열로 전환할때 사용
	 */
	private static  void testFlatMap(){
		//문자열을 , 기준으로 split 하여 배열로 변환
		Stream<String> s = Stream.of("a,e", "b,f", "c,g", "d,h");
		List<String> li = s.flatMap(al -> Stream.of(al.split(","))).collect(Collectors.toList());
		li.forEach(System.out::println);
	}
	
	/**
	 * 여기 설명
	 * 하나의 값을 추출해야 할때
	 * 비교식 또는 집계
	 * https://www.baeldung.com/java-stream-reduce
	 */
	private static void testReduce() {
		//length 길이가 제일 긴 값 구하기
		Stream<String> s = Stream.of("a", "b1", "c12", "d123");
		Optional<String> result = s.reduce((e, e1) -> {
			if(e.length() > e1.length()) {
				return e;
			}else {
				return e1;
			}
		});
		System.out.println(result);
		
		//비교대상 첫번째 값을 지정
		Stream<String> s2 = Stream.of("a", "b1", "c12", "d123");
		String result2 = s2.reduce("c14442", (e, e1) -> {
			System.out.println(e+" ,"+ e1);
			if(e.length() > e1.length()) {
				return e;
			}else {
				return e1;
			}
		});
		System.out.println(result2);
		
	}
}
