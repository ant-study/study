package kr.co.ant.study.moonjonghun.oop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ant.study.moonjonghun.oop.domain.MoonPaymentVO;
import kr.co.ant.study.moonjonghun.oop.domain.MoonReceiptVO;
import kr.co.ant.study.moonjonghun.oop.domain.Payment;
import kr.co.ant.study.moonjonghun.oop.payment.BankPayment;
import kr.co.ant.study.moonjonghun.oop.payment.CardPayment;
import kr.co.ant.study.moonjonghun.oop.payment.MobilePayment;
import kr.co.ant.study.moonjonghun.oop.payment.PaymentFactory;
import kr.co.ant.study.moonjonghun.oop.validation.FixedLengthValidation;
import kr.co.ant.study.moonjonghun.oop.validation.MinlengthValidation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MoonPaymentServiceImpl implements MoonPaymentService{
	
//	@Autowired
//	Payment payment;
	
	@Autowired
	MoonPaymentFacadeService facadeService;

	
	@Override
	public MoonReceiptVO cardPayment(MoonPaymentVO vo) {
		// TODO Auto-generated method stub
		CardPayment cardPayment = new CardPayment(vo, new FixedLengthValidation());
		MoonReceiptVO paymentResponse = facadeService.doPayment(cardPayment);
		return paymentResponse;
	}

	@Override
	public MoonReceiptVO bankAccountPayment(MoonPaymentVO vo) { 
		BankPayment bankPayment = new BankPayment(vo, new FixedLengthValidation());
		MoonReceiptVO paymentResponse = facadeService.doPayment(bankPayment);
		return paymentResponse;
	}

	@Override
	public MoonReceiptVO mobilePayment(MoonPaymentVO vo) {
		MobilePayment mobilePayment = new MobilePayment(vo, new MinlengthValidation());
		MoonReceiptVO paymentResponse = facadeService.doPayment(mobilePayment);
		return paymentResponse;
	}
	
	@Override
	public MoonReceiptVO compositePayment(MoonPaymentVO vo) throws Exception{
		//팩토리 생성에는 
		PaymentFactory factory = new PaymentFactory();
		Payment payment = factory.getPayment(vo, vo.getPaymentType());
		MoonReceiptVO paymentResponse = facadeService.doPayment(payment);
		return paymentResponse;
	}

}
