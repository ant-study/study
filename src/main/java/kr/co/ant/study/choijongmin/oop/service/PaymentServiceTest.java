package kr.co.ant.study.choijongmin.oop.service;

import org.springframework.stereotype.Service;

import kr.co.ant.study.choijongmin.oop.vo.PaymentInfo;

@Service
public class PaymentServiceTest {
	
	private static String payTypeCard = "Card";
	private static String payTypeBank = "Bank";
	private static String payTypeMobile = "Mobile";
	
	public void paymentTypeClassification(PaymentInfo info) {
		
		if ( info.getPaymentType().equals(payTypeCard) ) {
			
		} else if( info.getPaymentType().equals(payTypeBank) ) {
			
		} else if ( info.getPaymentType().equals(payTypeMobile) ) {
			
		};
		
	}
	
}