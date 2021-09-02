package kr.co.ant.study.student.moonjonghun.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoonBasicStream {
	
	public static void main(String[] args) {
//		basicListStream();
//		basicStream();
//		filterStream();
//		filterAndFindStream();
		streamParallel();
	}
	
	public static void basicListStream() {
		List<String> list = listedData();
		List<String> newList = list.stream().map(al -> al+"-1").collect(Collectors.toList());
		
		//관계형 메소드 사용하여 출력
		newList.forEach(System.out::println);
//		Stream<String> a = list.stream().filter((t) -> t.equals("c"));
//		System.out.println("basicListStream ######### stream data #######{"+a.toString()+"}");  
	};
	
	public static void basicStream() {
		Stream<String> stream = streamData();
		boolean result = stream.allMatch((t) -> t.startsWith("a"));
		System.out.println("basicStream ######### allMatch ####### {"+result+"}");
	};
	
	public static void filterStream() {
		Stream<String> stream = streamData();
		Stream<String> filterData = stream.filter((a) -> a.startsWith("a"));
		filterData.forEach((f) -> System.out.println("씨수아우트"+f));
		
	};
	
	public static void filterAndFindStream() {
		Stream<String> stream = streamData();
		//findAny 는 스트림의 순서와 상관없이 가장 먼저 찾아지는것을 return
		//병렬처리가 아닐경우 findAny와 findFirst 의 값이 같다.
		Optional<String> as =stream.filter((t)-> t.startsWith("a")).findAny();
		
		//findFirst 는 스트림의 순서상에서 가장 앞에있는것을 return
		Optional<String> as2 = stream.filter((t)-> t.startsWith("a")).findFirst();
		
		System.out.println(as.get());
		System.out.println(as2.get());
	}
	
	public static void streamParallel() {
		Stream<String> single = streamData();
		single.forEach((t) -> System.out.println("Thread Name :: "+Thread.currentThread().getName()+"  ===>"+t));
		System.out.println("=================================================================================");
		System.out.println("========= 멀티쓰레드 ==========");
		//Thread Pool에 대기중인 connection에 랜덤으로 할당되기때문에 조회할때마다 값이 다름
		Stream<String> multi = streamData();
		multi.parallel().forEach((t) -> System.out.println("Thread Name :: " + Thread.currentThread().getName()+" ===>"+t));
		
		
	}
	
	
	public static List<String> listedData(){
		//String형을 가지고있는 stream list를 반환한다.
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		return list;
	};
	
	public static Stream<String> streamData(){
		return Stream.of("a","b","ac","d","e","af");
	}
}
