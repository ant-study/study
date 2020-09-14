package kr.co.ant.study.moonjonghun.oop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.moonjonghun.oop.domain.MoonPayDTO;
import kr.co.ant.study.moonjonghun.oop.pg.MoonPGClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MoonPaymentController {
	
	@Autowired
	MoonPGClient client;
	
	@Autowired
	ObjectMapper mapper;
	
	@RequestMapping("/moon/card")
	@ResponseBody
	public Object getPayInfoByCard(MoonPayDTO info) throws Exception{
		//카드결제정보를 가지고 결제처리
		log.info("PaymentInfo ::: {}", info);
		Object obj = client.doPayment(mapper.writeValueAsString(info));
		
		return obj;
	} 
	
	@RequestMapping("/moon/mobile")
	@ResponseBody
	public Object getPayInfoByMobile(MoonPayDTO info) throws Exception{
		//휴대폰결제정보를 가지고 결제처리
		log.info("PaymentInfo ::: {}", info);
		Object obj = client.doPayment(mapper.writeValueAsString(info));
		return obj;
	};
	
	@RequestMapping("/moon/bank")
	@ResponseBody
	public Object getPayInfoByBankAccount(MoonPayDTO info) throws Exception{
		//계좌이체결제정보를 가지고 결제처리
		log.info("PaymentInfo ::: {}", info);
		Object obj = client.doPayment(mapper.writeValueAsString(info));
		return obj;
	}
	
	@RequestMapping("/moon/AllInOnePay")
	@ResponseBody
	public Object getPayInfoAllInOne(MoonPayDTO info) throws Exception{
		
		return null;
	}
	
}
