package kr.co.ant.study.hankwangsu.generic;

public class GenericClassOtherImpl<T> implements IGenericClass<T>{

	
	public T processGeneric(T t) {
		return t;
	}
	
	public static void main(String[] args) {
		GenericClassOtherImpl<Super> superGeneric = new GenericClassOtherImpl<Super>();
		GenericClassOtherImpl<Other> otherGeneric = new GenericClassOtherImpl<Other>();
		GenericClassOtherImpl<Parent> parentGeneric = new GenericClassOtherImpl<Parent>();
		GenericClassOtherImpl<FirstChild> firstChildGeneric = new GenericClassOtherImpl<FirstChild>();
		
	}


}
