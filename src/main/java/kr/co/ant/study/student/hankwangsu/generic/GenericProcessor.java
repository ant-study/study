package kr.co.ant.study.hankwangsu.generic;

import org.springframework.util.NumberUtils;

public class GenericProcessor {

	/**
	 * 기본 제네릭 ==> 관례적으로 Type이 올경우 T, List<String> 과 같이 Element가 올경우 E, 리턴값인 경우 R
	 * @param <T>
	 * @param t
	 * @return
	 */
	public static <T> T process(T t) {
		//t.print();
		return t;
	}
	
	
	public static <T extends Parent> T processGeneric(T t) {
		
		if(!t.isPrinted()) {
			t.print();
			t.setPrinted(true);
		}
		return t;
	}
	
	
	public static Parent processNonGeneric(Parent t) {
		
		if(!t.isPrinted()) {
			t.print();
			t.setPrinted(true);
		}
		return t;
	}
	
	public static <T extends Parent> T processGenericNewInstace(Class<T> t, String initMessage) throws Exception {
		T p = t.newInstance();
		p.init(initMessage);
		return p;
	}
	
	public static void main(String[] args) throws Exception {
		FirstChild c1 = new FirstChild();
		
		FirstChild c2 = process(c1); 
		//Child2 c2 = process(c1); //Compile 오류
		
		//T 타입이 Parent이거나 Parent를 상속 받은것만 받겠다 다른거 오면 Compile 오류
		FirstChild c3 = processGeneric(c1);
		
		/*
		Super s1 = new Super();
		processGeneric(s1); //Compile 오류
		*/
		
		//Generic 쓴거와 안쓴거의 차이점
		FirstChild c4 = (FirstChild) processNonGeneric(c1);
		//SecondChild c5 = (
		
		FirstChild c41 = processGeneric(c1);
		
		FirstChild nc = processGenericNewInstace(FirstChild.class, "test1");
		FirstChild p = processGeneric(nc);
	}
	
	
	
}
