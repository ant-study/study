package kr.co.ant.study.imsoyeon.d_oop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ant.study.imsoyeon.d_oop.domain.RequestPayInfo;
import kr.co.ant.study.imsoyeon.d_oop.payment.CardPayment;
import kr.co.ant.study.imsoyeon.d_oop.validate.AccountValidator;
import kr.co.ant.study.imsoyeon.d_oop.validate.CardValidator;
import kr.co.ant.study.imsoyeon.d_oop.validate.MobileValidator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Service
@Getter
@Setter
@ToString
public class PaymentServiceY {
	
//	이렇게 하고싶지 않은데
	private CardValidator cardValidator;
	private AccountValidator accountValidator;
	private MobileValidator mobileValidator;

	@Autowired
	private PaymentFacadeY facade;
	
	public void doCardPay(RequestPayInfo inputVO) throws Exception {
		CardPayment card = new CardPayment(inputVO, cardValidator);
		
		facade.doPayment(card);
	}
	
	public void doAccountPay(RequestPayInfo inputVO) throws Exception {
		
	}
	
	public void doMobilePay(RequestPayInfo inputVO) throws Exception {
		
		
	}
	
		
}
