package kr.co.ant.study.moonjonghun.oop.domain;

public interface Payment {
	
	public <T> String validate(T paymentInfo) throws Exception; //확장성이 부족하여 사용하지 않겠음
	
	public void fixedLengthValidate(String s, int fixedLength) throws Exception;

	public void minLengthValidate(String s, int minLength) throws Exception;
	
	//거래정보를 PG전송을 위한 데이터로 변환
	public <T, U> Object convertToPG(T pgData, U voData) throws Exception;
	
	
}
