package kr.co.ant.study.imsoyeon.d_oop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.ant.study.imsoyeon.d_oop.domain.RequestPayInfo;
import kr.co.ant.study.imsoyeon.d_oop.service.PaymentServiceY;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PaymentControllerY {
	
	@Autowired
	private PaymentServiceY service;

	@ResponseBody
	@RequestMapping(value = "/payCard")
	public String payCard(RequestPayInfo request) throws Exception {
		
//		service.doCardPay(request);
		service.compositePayment(request);
		
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping(value = "/payAccount")
	public String payAccount(RequestPayInfo request) throws Exception {
		
		service.doAccountPay(request);
		
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping(value = "/payMobile")
	public String payMobile(RequestPayInfo request) throws Exception {
		
		service.doMobilePay(request);
		
		return "ok";
	}
}
