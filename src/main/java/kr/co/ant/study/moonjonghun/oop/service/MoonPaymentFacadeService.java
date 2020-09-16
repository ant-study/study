package kr.co.ant.study.moonjonghun.oop.service;

import kr.co.ant.study.moonjonghun.oop.domain.MoonReceiptVO;
import kr.co.ant.study.moonjonghun.oop.domain.Payment;

public interface MoonPaymentFacadeService {
	public MoonReceiptVO doPayment(Payment p);
}
