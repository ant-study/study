package kr.co.ant.study.moonjonghun.oop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.moonjonghun.oop.domain.MoonPaymentVO;
import kr.co.ant.study.moonjonghun.oop.service.MoonPaymentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MoonPaymentController {
	
//	@Autowired
//	MoonPGClient client;
	
	@Autowired
	MoonPaymentService client;
	
	@Autowired
	ObjectMapper mapper;
	
	@RequestMapping("/moon/card")
	@ResponseBody
	public Object getPayInfoByCard(MoonPaymentVO info){
		try {
			//카드결제정보를 가지고 결제처리
			log.info("PaymentInfo ::: {}", info);
//			Object obj = client.doPayment(mapper.writeValueAsString(info), info);
//			Object obj = client.cardPayment(info);
			Object obj = client.compositePayment(info);
			
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	} 
	
	@RequestMapping("/moon/mobile")
	@ResponseBody
	public Object getPayInfoByMobile(MoonPaymentVO info) {
		try {
			//휴대폰결제정보를 가지고 결제처리
			log.info("PaymentInfo ::: {}", info);
//			Object obj = client.doPayment(mapper.writeValueAsString(info), info);
//			Object obj = client.mobilePayment(info);
			Object obj = client.compositePayment(info);
			
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	};
	
	@RequestMapping("/moon/bank")
	@ResponseBody
	public Object getPayInfoByBankAccount(MoonPaymentVO info) throws Exception{
		try {
			//계좌이체결제정보를 가지고 결제처리
			log.info("PaymentInfo ::: {}", info);
//			Object obj = client.doPayment(mapper.writeValueAsString(info), info);
//			Object obj = client.bankAccountPayment(info);
			Object obj = client.compositePayment(info);
			
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
