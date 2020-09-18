package kr.co.ant.study.student.hankwangsu.generic;

import java.util.stream.Stream;

public class GenericClassDeclareImpl implements IGenericClass<Other>{

	@Override
	public Other processGeneric(Other t) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static class Test implements IGenericClass<Parent>{

		@Override
		public Parent processGeneric(Parent t) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	
	public static <T> void main(String[] args) {
		
		IGenericClass<?> otherGeneric = new GenericClassDeclareImpl();
		otherGeneric = new Test();
		
		proc(otherGeneric);


	}
	
	public static <T> void proc(IGenericClass<T> i ) {
		
	}

}
