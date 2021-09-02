package kr.co.ant.study.student.imsoyeon.d_oop.service;

import java.util.function.BiPredicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ant.study.student.imsoyeon.d_oop.domain.RequestPayInfo;
import kr.co.ant.study.student.imsoyeon.d_oop.payment.AccountPayment;
import kr.co.ant.study.student.imsoyeon.d_oop.payment.CardPayment;
import kr.co.ant.study.student.imsoyeon.d_oop.payment.MobilePayment;
import kr.co.ant.study.student.imsoyeon.d_oop.payment.Payment;
import kr.co.ant.study.student.imsoyeon.d_oop.pg.vo.PaymentFactoryY;
import kr.co.ant.study.student.imsoyeon.d_oop.validate.FixLengthValidatorY;
import kr.co.ant.study.student.imsoyeon.f_functional.second.BiPredicateValidatorYImpl;
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
	
	private BiPredicate<String, Integer> fixFunc = (v, l) -> v.length() != l;
	private BiPredicate<String, Integer> minFunc = (v, l) -> v.length() < l;
	
	public void doCardPay(RequestPayInfo inputVO) throws Exception {
		
//		1.d_oop Test
		CardPayment card1 = new CardPayment(inputVO, new FixLengthValidatorY());
		
//		2.f_functional Test
		CardPayment card2 = new CardPayment(inputVO, new BiPredicateValidatorYImpl(fixFunc));
		facade.doPayment(card2);
	}
	
	public void doAccountPay(RequestPayInfo inputVO) throws Exception {
		
		AccountPayment account = new AccountPayment(inputVO, new BiPredicateValidatorYImpl(minFunc));
		
		facade.doPayment(account);
	}
	
	public void doMobilePay(RequestPayInfo inputVO) throws Exception {
		
		MobilePayment mobile = new MobilePayment(inputVO, new BiPredicateValidatorYImpl(fixFunc));
		
		facade.doPayment(mobile);
	}
	
	public void compositePayment(RequestPayInfo inputVO) throws Exception {
		
//		factory pattern
		Payment payment = PaymentFactoryY.createPayment(inputVO);
		
		facade.doPayment(payment);
	}
	
		
}
