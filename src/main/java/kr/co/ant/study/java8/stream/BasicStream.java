package kr.co.ant.study.java8.stream;

import java.util.Optional;
import java.util.stream.Stream;

public class BasicStream {
	
	/**
	 * Stream의 특징
	 *  - 일회성이다. 이말은 한번 사용하고 나면 재사용 할 수 없다.
	 * @param args
	 */
	public static void main(String[] args) {
		streamSignleUse();
		streamFilter();
		streamFilterAndFind();
		streamParallel();
	}
	
	/**
	 * Stream은 아래와 같이 한번 사용하고 나서 또 읽어드릴경우 에러가 발생한다.
	 * Stream은 일회성이기 때문에 또 사용 하지말자
	 */
	public static void streamSignleUse() {
		System.out.println("Stream 재사용 오류 테스트 시작");
		Stream<String> s = testData();
		s.forEach(System.out::println);
		try {
			s.forEach(System.out::println);
		}catch(Exception e) {
			System.err.println("Stream 재사용으로 인한 오류 ::: "+e.getMessage());
		}
		System.out.println("Stream 재사용 오류 테스트 끝");
		printDivideLine();
	}
	
	/**
	 * filter메소드는  Argument를 Predicate를 받는다. filter의 결과 값은 또 Stream이다.
	 * Predicate는 Argument 하나를 받고 boolean을 리턴한다.
	 */
	public static void streamFilter() {
		System.out.println("Stream Filter 테스트 시작");
		System.out.println("a로 시작하는것만 가져온다.");
		Stream<String> s = testData();
		
		//a로 시작하는 것만 다시 Stream으로 변환
		Stream<String> result = s.filter((t) -> t.startsWith("a"));
		
		//forEach의 Argument는 Consumer다 말그대로 소비만 하고 리턴값은 없다.
		result.forEach(System.out::println);
		//or result.forEach((f) -> System.out.println(f));
		System.out.println("Stream Filter 테스트 끝");
		printDivideLine();
	}
	
	/**
	 * findAny, findFirst는 일반적으로 리턴 값이 동일하나
	 * 병렬처리일 경우 결과 값이 다를 수 있다.
	 * 정말 첫번째 값이 필요하다면 findFirst를 사용하자
	 */
	public static void streamFilterAndFind() {
		System.out.println("Stream find 메소드 테스트 시작");
		Stream<String> s = testData();
		
		//findAny, findFirst
		Optional<String> op = s.filter((t) -> t.startsWith("a"))
			.findAny();
		System.out.println(op.get());
		System.out.println("Stream find 메소드 테스트 끝");
		printDivideLine();
		
	}
	
	/**
	 * 병렬처리
	 */
	public static void streamParallel() {
		System.out.println("Stream 병렬처리 테스트 시작");
		System.out.println("Single Thread 테스트 시작");
		
		Stream<String> single = testData();
		
		single.forEach((t) -> System.out.println("Thread Name :: "+Thread.currentThread().getName()+" ==> "+t));
		
		System.out.println("Single Thread 종료");
		System.out.println("=========================================");
		System.out.println("Multi Thread 시작");
		
		Stream<String> s = testData();
		s.parallel().forEach((t) -> System.out.println("Thread Name :: "+Thread.currentThread().getName()+" ==> "+t));
		System.out.println("Multi Thread 종료");
		System.out.println("Stream 병렬처리 테스트 끝");
	}
	
	private static Stream<String> testData(){
		return Stream.of("a-1", "a-2", "a-3", "b-1", "b-2", "b-3", "c-1", "c-2");
	}
	private static void printDivideLine() {
		System.out.println("===========================================");
	}
}
