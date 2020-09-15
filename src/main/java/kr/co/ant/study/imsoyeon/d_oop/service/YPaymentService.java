package kr.co.ant.study.imsoyeon.d_oop.service;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ant.study.imsoyeon.d_oop.domain.RequestPayInfo;
import kr.co.ant.study.imsoyeon.d_oop.payment.CardPayment;
import kr.co.ant.study.imsoyeon.d_oop.vo.ANTCardPayInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class YPaymentService {

	@Autowired
	private YPaymentFacade facade;
	
	public void doPay(RequestPayInfo request) throws Exception {
		
		ANTCardPayInfo cardData = new ANTCardPayInfo();
		PropertyUtils.copyProperties(cardData, request);
		
		log.info("TEST : {}", cardData);
//		facade 호출
//		facade.doPayment(p);
	}
	
	public void doCardPay(RequestPayInfo request) throws Exception {
		CardPayment a= new CardPayment();
		
		a.va
		a.co\
		cl.pa(ss)
		
	}
	
		
}
