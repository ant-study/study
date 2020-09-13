package kr.co.ant.study.vvooss;


import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter

class MaTest {

	String name;
	int   age;
	
	MaTest(){
		System.out.println("MaTest default constructor");
		age = 0;
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
		log.debug("0. addr info b'addr = {} , a.addr = {} , c.addr= {}", b, a, c);
		a.setName("a");
		a.setAge(30);
		b = a;
		// 대입하고 난 뒤에 실제 값이 대입된게 아닌, a의 주소가 b로 대입된걸 확인한다.
		log.debug("1. addr info b'addr = {} , a.addr = {} , c.addr= {}", b, a, c);
		log.debug("1. b'name = {} , a.name = {} , c.name= {}", b.getName(), a.getName(), c.getName());
		b.setName("b");
		b.setAge(40);
		log.debug("2. b'name = {} , a.name = {} , c.name= {}", b.getName(), a.getName(), c.getName());
		log.debug("2. b'age = {} , a.age = {} , c.age= {}", b.getAge(), a.getAge(), c.getAge());
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
		
		callByL_Value(c,50);
		
		log.debug("4. b'name = {} , a.name = {} , c.name= {}", b.getName(), a.getName(), c.getName());
	}
	static void callByL_Value(MaTest l, int age) {
		MaTest inV = new MaTest();
		inV = l;
		inV.setAge(age);
		inV.setName("this is callByL_Value");
		log.debug("after callBYL_Value :: {},age{}" ,l.getName(),l.getAge());
		log.debug("this is callByL_Value inV {},age{}",inV.getName(),inV.getAge()); 
	}
}