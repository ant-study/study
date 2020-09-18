package kr.co.ant.study.student.hankwangsu.generic;

public class Parent {
	
	private boolean printed;
	
	private String name;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void init(String s) {
		System.out.println("초기화 작업중 ::: "+s);
	}
	
	public void print() {
		System.out.println("parent");
	}
	
	public Super getSuper() {
		return new Super();
	}

	public boolean isPrinted() {
		return printed;
	}

	public void setPrinted(boolean printed) {
		this.printed = printed;
	}
	
	
	
}
