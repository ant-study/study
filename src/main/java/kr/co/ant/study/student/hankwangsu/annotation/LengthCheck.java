package kr.co.ant.study.hankwangsu.annotation;

import java.util.function.BiPredicate;

public enum LengthCheck {
	
	LESS((base, dest) -> base >= dest, "이하"),
	MORE((base, dest) -> base <= dest, "이상");
	
	private BiPredicate<Integer, Integer> p;
	private String signName;
	
	/**
	 * java8 functional interface 사용
	 * 
	 * Predicate => 인자값 한개만 받을수 있음
	 * BiPredicate => 인자값 두개 받을수 있음
	 * @param p
	 * @param signName
	 */
	private LengthCheck(BiPredicate<Integer, Integer> p, String signName) {
		this.p = p;
		this.signName = signName;
	}
	
	public boolean test(int base, int dest) {
		return p.test(base, dest);
	}
	
	public String signName() {
		return signName;
	}
}
