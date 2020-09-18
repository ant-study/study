package kr.co.ant.study.student.imsoyeon.b_generics;

public class test {

	public void test1(Class clazz) {
		
	}
	
	public void test2(Class<?> clazz) {
		
	}
	
	public static void main(String[] args) throws Exception {
		
		String a = "test";
		String b = "test";
		String c = new String("test");
		
		boolean t = false;
		t = (a == b);
		t = (a == c);
		t = a.equals(c);
		
		c = a;
		
		a = "xx";
		t = (a == c);
	}
}
