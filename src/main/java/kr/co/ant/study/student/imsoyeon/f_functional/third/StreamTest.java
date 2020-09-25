package kr.co.ant.study.student.imsoyeon.f_functional.third;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream
 * 		Collections / Array를 다루기 위한 클래스(인터페이스)들이 有
 * 		각각의 메서드들을 가지고 있기 때문에, 그 중엔 서로 기능이 겹치는 메서드들이 있음. 적절한 걸로 골라 쓰면 되지만, 복잡~
 * 		그래서 Stream을 만듦.
 * 
 * 		↓ 자바의 정석 中
 * 		-------------
 * 		Stream은 데이터 소스를 추상화하고, 데이터를 다루는데 자주 사용되는 메서드들을 정의해 놓았다.
 * 		데이터 소스를 추상화하였다는 것은, 데이터 소스가 무엇이던 간에 같은 방식으로 다룰 수 있게 되었다는 것과
 * 		코드의 재사용성이 높아진다는 것을 의미한다.
 * 		Stream을 이용하면, 배열이나 컬렉션 뿐만 아니라 파일에 저장된 데이터도 모두 같은 방식으로 다룰 수 있다.
 * 		-------------
 * */
public class StreamTest {

	public static void main(String args[]) {
		
//		Array : Stream 생성
		String[]  strArr = {"aaa", "bbb", "ccc"};
		Stream<String> strStream1 = Arrays.stream(strArr);
		
//		List : Stream 생성
		List<String> strList = Arrays.asList(strArr);
		Stream<String> strStream2 = strList.stream();
		
		/*
		Stream sort & print 방법
		sort 시 원래의 데이터 소스 자체를 정렬시키는게 아님 
		stream은 데이터 소스를 직접 제어할 수 있게 해주는게 아니라 데이터 소스를 읽어와서 읽어온걸 다룰 수 있게 해주는 거라고 이해하면 될 것같은데
		 * */
		strStream1.sorted().forEach(System.out::println);
		printDivideLine();
		strStream2.sorted().forEach(System.out::println);

		/*
		forEach()
		 	: 내부반복용 메서드.
		반복문을 소스상에서 드러내지않고, 메서드 내에 숨겨서 반복문을 돌게할 수있음.
		매개변수로 람다식을 받아서, 데이터 소스 내의 모든 데이터에 같은 내용을 적용한다.
		
		void java.util.stream.Stream.forEach(Consumer<? super String> action)
		 * */
		
		printDivideLine();
//		데이터 소스 별로 sort & pring 방법 ( Stream X )
		Arrays.sort(strArr);
		for (String string : strArr) {
			System.out.println(string);
		}
		
		Collections.sort(strList);
		for (String string : strList) {
			System.out.println(string);
		}
		
		
//		중간연산 최종연산
//		지연된 연산
//		병렬 연산 시키고, 병렬 연산 안하도록 시키고
		
		/*
		컬렉션의 최고 조상인 COllention에 stream()이 정의되어 있다.
		그래서 Collention의 자손인 List와 SEt을 구현한 컬렉션 클래스들은 모두 이 메서드로 스트림을 생성할 수 있다.
		stream()은 해당 컬렉션을 소스로 ㅎ는 스트림을 반환한다.
		한가지 주의할 점은 forrEach()가 스트림의 요소를 소모하면서 ㅈ가업을 수행하므로 같은 스트림에 forEach()를 두 번 호출할 수 없다는 것이다.
		그래서 스트림의 요소를 한번 더 출력하려면 스트림을 새로 생성해야 한다.
		 * */
		
		
		
	}
	
	public static void printDivideLine() {
		System.out.println("====================");
	}
}
