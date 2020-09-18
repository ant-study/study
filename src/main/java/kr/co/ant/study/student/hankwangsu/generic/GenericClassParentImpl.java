package kr.co.ant.study.student.hankwangsu.generic;

public class GenericClassParentImpl<T extends Parent> implements IGenericClass<T>{

	
	public T processGeneric(T t) {
		t.print();
		return t;
	}
	
	public static void main(String[] args) {
		
		//이제 이 클래스는 FirstChild만 받을수 있음
		GenericClassParentImpl<FirstChild> g = new GenericClassParentImpl<FirstChild>();
		
		//이제 이 클래스는 SecondChild만 받을수 있음
		GenericClassParentImpl<SecondChild> g2 = new GenericClassParentImpl<SecondChild>();
		
		
	}

}
