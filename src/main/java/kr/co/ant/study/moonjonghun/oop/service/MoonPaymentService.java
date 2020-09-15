package kr.co.ant.study.moonjonghun.oop.service;

import kr.co.ant.study.moonjonghun.oop.domain.MoonPaymentVO;

public interface MoonPaymentService{
	public <T extends MoonPaymentVO> Object doPayment(String json, T obj) throws Exception;
}
