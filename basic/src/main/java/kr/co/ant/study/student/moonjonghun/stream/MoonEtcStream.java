package kr.co.ant.study.student.moonjonghun.stream;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoonEtcStream {
	
	public static void main(String[] args) {
//		testMap();
//		testReduce();
//		testCollect();
		testLimit();
	}
	
	public static void testMap() {
		
		//Stream Data의 값을 편집하고싶을때 Map을 사용한다.
		
		//원본 Stream Data뒤에 문자열을 붙히고 싶을때
		List<String> li = streamData().map(al -> al+"-1").collect(Collectors.toList());
		li.forEach(System.out::println);
		
		//원본 Stream Data의 길이를 list로 뽑고싶을때
		List<Integer> li2 = streamData().map(al ->al.length()).collect(Collectors.toList());
		li2.forEach(System.out::println);
	}
	
	public static void testFlatMap() {
		Stream<String> stream = streamData();
		
	}
	
	public static void testReduce() {
		//스트림의 첫번째 값과 다음값을 비교하고
		//return 값을 다음 reduce의 첫번째 인자값으로 사용한다.
		System.out.println("reduce In");
		Optional<String> result = streamData().reduce((e, e1) -> {
			System.out.println(e+",  "+e1);
			//인자값 2개를 전달받아 비교한다.
			if("ac".equals(e)) {
				return e;
			}else {
				return e1;
			}
		});
		System.out.println("reduce Out");
		System.out.println(result.get());
	}
	
	public static void testCollect() {
		
		System.out.println("################### Joining Test #########################");
		Stream<String> stream = streamData();
		//stream data를 string으로 변환하고 joining으로 하나로 합쳐준다.
		String result = stream.map(Object::toString).collect(Collectors.joining(", "));
		System.out.println(result);
		System.out.println("########################################################## ");
		
		System.out.println("################### toMap Test   ######################### ");
		Stream<MoonToMapTest> stream2 = Stream.of(new MoonToMapTest(1,"banana"), new MoonToMapTest(2, "apple")
				, new MoonToMapTest(3, "lemon")
				, new MoonToMapTest(3, "kiwi")
				, new MoonToMapTest(4, "peach"));
		Map<Integer, String> map = stream2.collect(Collectors.toMap(item -> item.getIdx(), item -> item.getData(),
				(exVal , newVal) -> exVal));
		map.forEach((idx, value) ->{ System.out.println("idx ===="+idx+"////value ===="+value);});
		System.out.println("########################################################## ");
		
		System.out.println("################### average Test ######################### ");
		Stream<Integer> stream3 = Stream.of(1,2,3,4,5);
		Double result3 = stream3.collect(Collectors.averagingDouble((v) -> v *1.3));
		System.out.println("Average Value :: "+result3);
		System.out.println("########################################################## ");
	}
	
	public static void testLimit() {
		Stream<String> stream = streamData();
		//5개의 스트림 데이터만 출력된다.
		Stream<String> streamLim = stream.limit(5);
		streamLim.forEach((s) -> System.out.println(s));
	}
	
	
	public static Stream<String> streamData(){
		return Stream.of("aa","ab","ac","ad","ae","af");
	}
}
