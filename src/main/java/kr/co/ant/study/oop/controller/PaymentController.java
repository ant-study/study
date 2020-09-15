package kr.co.ant.study.oop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.oop.domain.PaymentInfoVO;
import kr.co.ant.study.oop.pg.ANTPGClient;
import kr.co.ant.study.oop.service.PaymentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PaymentController {
	
	
	@Autowired
	private PaymentService service;
	
	
	@ResponseBody
	@RequestMapping("/hks/pay/card")
	public String paymentCard(PaymentInfoVO info) throws Exception{
		service.paymentCard(info);
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/hks/pay/mobile")
	public String paymentMobile(PaymentInfoVO info) throws Exception{
		service.paymentMobile(info);
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/hks/pay/bank")
	public String paymentBank(PaymentInfoVO info) throws Exception{
		service.paymentBank(info);
		return "ok";
	}
}
