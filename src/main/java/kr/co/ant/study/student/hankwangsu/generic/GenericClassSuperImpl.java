package kr.co.ant.study.student.hankwangsu.generic;

public class GenericClassSuperImpl<T extends Super> implements IGenericClass<T>{

	
	public T processGeneric(T t) {
		t.printSuper();
		return t;
	}
	
	public static void main(String[] args) {
		FirstChild a=  new FirstChild();
		Parent p = new Parent();
		GenericClassSuperImpl<Super> superGeneric = new GenericClassSuperImpl<Super>();
		
	}

}
