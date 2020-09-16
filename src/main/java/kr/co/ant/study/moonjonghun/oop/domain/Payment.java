package kr.co.ant.study.moonjonghun.oop.domain;

import kr.co.ant.study.moonjonghun.oop.exception.ValidateException;
import kr.co.ant.study.moonjonghun.oop.pg.MoonPaymentInfo;

public interface Payment {
	
//	public <T> String validate(T paymentInfo) throws Exception; //확장성이 부족하여 사용하지 않겠음
	
	//거래정보를 PG전송을 위한 데이터로 변환
	public <T, U> Object convertToPG(T pgData, U voData) throws Exception;
	
	//거래정보를 변환
	public MoonPaymentInfo convertPayToPG() throws Exception;
	
	//validation
	public void validate() throws ValidateException;
	
	//logging
	public void logging(String type, int amt);
	
}
