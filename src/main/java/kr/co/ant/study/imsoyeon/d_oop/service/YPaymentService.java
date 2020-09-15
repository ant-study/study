package kr.co.ant.study.imsoyeon.d_oop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ant.study.imsoyeon.c_oop.domain.RequestPaySample;

@Service
public class YPaymentService {

	@Autowired
	private YPaymentFacade facade;
	
	public void doPay(RequestPaySample request) throws Exception {
		
		
//		facade.doPayment(p);
	}
}
