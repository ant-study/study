package kr.co.ant.study.moonjonghun.oop.service;

import kr.co.ant.study.moonjonghun.oop.domain.MoonPaymentVO;
import kr.co.ant.study.moonjonghun.oop.domain.MoonReceiptVO;

public interface MoonPaymentService{
//	public <T extends MoonPaymentVO> Object doPayment(String json, T obj) throws Exception; //결제를 공통으로 처리하는 함수
	
	public MoonReceiptVO cardPayment(MoonPaymentVO vo); //신용카드 결제
	
	public MoonReceiptVO bankAccountPayment(MoonPaymentVO vo); //계좌결제
	
	public MoonReceiptVO mobilePayment(MoonPaymentVO vo); // 모바일결제
}
