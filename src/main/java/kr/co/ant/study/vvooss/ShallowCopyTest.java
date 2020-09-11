package kr.co.ant.study.vvooss;


import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter

class MaTest {

	String name;
	MaTest(){
		System.out.println("MaTest default constructor");
	}
	MaTest(String s) {
		System.out.println("MaTest custom constructor with string");
		this.name=s;
	}
}
@Slf4j
public class ShallowCopyTest {
	
	public static void main (String[] args) {
		MaTest a = new MaTest();
		MaTest b = new MaTest();
		MaTest c = new MaTest("C");
		a.setName("a");
		b = a;
		log.debug("1. b'name = {} , a.name = {} , c.name= {}", b.getName(), a.getName(), c.getName());
		b.setName("b");
		log.debug("2. b'name = {} , a.name = {} , c.name= {}", b.getName(), a.getName(), c.getName());
		
		ArrayList <MaTest> arA = new ArrayList<MaTest>();
		
		arA.add(a);
		arA.add(b);
		arA.add(c);
		
		//
		// arrayList 3번째 인덱스에 있는 객체는 주소일까 아니면 그 자체의 값일까?
		// add()는 call by referrence 일까 call by value 일까 ?
		//
		arA.get(arA.indexOf(c)).setName("D");
		
		log.debug("3. b'name = {} , a.name = {} , c.name= {}", b.getName(), a.getName(), c.getName());
		
		callByL_Value(c);
		
		log.debug("4. b'name = {} , a.name = {} , c.name= {}", b.getName(), a.getName(), c.getName());
	}
	static void callByL_Value(MaTest l) {
		l.setName("this is callByL_Value");
		log.debug("after callBYL_Value :: " + l.getName());
	}
}