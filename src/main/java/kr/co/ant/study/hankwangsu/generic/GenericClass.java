package kr.co.ant.study.hankwangsu.generic;

public class GenericClass<T extends Parent> {

	
	public T processGeneric(T t) {
		t.print();
		return t;
	}
	
	public Parent processNonGeneric(Parent t) {
		t.print();
		return t;
	}
	
	public static void main(String[] args) {
		FirstChild a=  new FirstChild();
		Parent p = new Parent();
		
		
		GenericClass<FirstChild> g = new GenericClass<FirstChild>();
		
		GenericClass<SecondChild> g2 = new GenericClass<SecondChild>();
	}
}
