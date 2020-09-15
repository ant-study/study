package kr.co.ant.study.imsoyeon.d_oop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ant.study.imsoyeon.d_oop.payment.CardPayment;
import kr.co.ant.study.imsoyeon.d_oop.pg.vo.AccountInfoVO;
import kr.co.ant.study.imsoyeon.d_oop.pg.vo.CardInfoVO;
import kr.co.ant.study.imsoyeon.d_oop.pg.vo.MobileInfoVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class YPaymentService {

	@Autowired
	private YPaymentFacade facade;
	
	public void doCardPay(CardInfoVO request) throws Exception {
		
	}
	
	public void doAccountPay(AccountInfoVO request) throws Exception {
		CardPayment a= new CardPayment();
		
		//validate등등 set해주고 PaymentFacade 호출
		
//		a.va
//		a.co\
//		cl.pa(ss)
		
	}
	
	public void doMobilePay(MobileInfoVO request) throws Exception {
		CardPayment a= new CardPayment();
		
		
	}
	
		
}
