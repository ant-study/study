package kr.co.ant.study.imsoyeon.d_oop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ant.study.imsoyeon.d_oop.domain.RequestPayInfo;
import kr.co.ant.study.imsoyeon.d_oop.payment.CardPayment;
import kr.co.ant.study.imsoyeon.d_oop.validate.FixLengthValidatiorY;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Service
@Getter
@Setter
@ToString
public class PaymentServiceY {

	@Autowired
	private PaymentFacadeY facade;
	
	public void doCardPay(RequestPayInfo inputVO) throws Exception {
		CardPayment card = new CardPayment(inputVO, new FixLengthValidatiorY());
		
		facade.doPayment(card);
	}
	
	public void doAccountPay(RequestPayInfo inputVO) throws Exception {
		
	}
	
	public void doMobilePay(RequestPayInfo inputVO) throws Exception {
		
		
	}
	
		
}
