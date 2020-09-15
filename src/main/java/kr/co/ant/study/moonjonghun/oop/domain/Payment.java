package kr.co.ant.study.moonjonghun.oop.domain;

public interface Payment {
	
	public <T> String validate(T paymentInfo) throws Exception;
	
}
