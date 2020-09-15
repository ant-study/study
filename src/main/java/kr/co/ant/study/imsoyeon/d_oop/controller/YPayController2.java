package kr.co.ant.study.imsoyeon.d_oop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.ant.study.imsoyeon.d_oop.domain.RequestPayInfo;
import kr.co.ant.study.imsoyeon.d_oop.service.YPaymentService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class YPayController2 {
	
	@Autowired
	private YPaymentService service;

	@ResponseBody
	@RequestMapping(value = "/testPgApiY")
	public String requestApi(RequestPayInfo request) throws Exception {
		log.info("PaymentInfo ::: {}", request);
		
		//Service 호출
		service.doPay(request);
		
		return "ok";
	}
}
