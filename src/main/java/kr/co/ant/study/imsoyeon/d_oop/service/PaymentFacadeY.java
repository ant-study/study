package kr.co.ant.study.imsoyeon.d_oop.service;

import org.springframework.stereotype.Component;

import kr.co.ant.study.imsoyeon.d_oop.payment.Payment;
import kr.co.ant.study.imsoyeon.d_oop.pg.vo.PGPaymentInfo;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PaymentFacadeY {

	/**
	 * 공통작업 처리
	 * 공통으로 처리하는 작업은 내가 다 함
	 * @param payment
	 */
	public void doPayment(Payment payment) throws Exception {
		
//		1. validation 체크
		payment.validate();
		
//		2. logging
		payment.logging();
		
//		3. convert
		/*
		payment.convertToPaymentVO(payment, inputVO);
		
		PaymentFacadeY에서 inputVO가지고 컨버트하려면, 여기에 또 inputVO 가져와야해 
		Payment 호출 용 메서드를 하나 만들면 될것같음		
		 */
		PGPaymentInfo vo = payment.convertToPaymentVO();
		
//		4. call API method
		payment.requestPGAPI(vo);
		
//		5. manager response
		
	}
}
