package kr.co.ant.study.moonjonghun.oop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.moonjonghun.oop.pg.MoonPGClient;
import kr.co.ant.study.oop.domain.PaySample;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PaymentController {
	
	@Autowired
	MoonPGClient client;
	
	@Autowired
	ObjectMapper mapper;
	
	@RequestMapping("/test")
	public String test(PaySample info) throws Exception{
		//MoonPGClient Service class를 사용해서 결제정보를 처리해보자
		log.info("PaymentInfo ::: {}", info);
		client.doPayment(mapper.writeValueAsString(info));
		return "ok";
	} 
	
}
