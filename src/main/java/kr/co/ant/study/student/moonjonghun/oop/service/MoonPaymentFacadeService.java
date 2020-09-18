package kr.co.ant.study.student.moonjonghun.oop.service;

import kr.co.ant.study.student.moonjonghun.oop.domain.MoonReceiptVO;
import kr.co.ant.study.student.moonjonghun.oop.domain.Payment;

public interface MoonPaymentFacadeService {
	public MoonReceiptVO doPayment(Payment p);
}
