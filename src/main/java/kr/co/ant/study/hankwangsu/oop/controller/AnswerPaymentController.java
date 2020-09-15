package kr.co.ant.study.hankwangsu.oop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.ant.study.hankwangsu.oop.service.AnswerPaymentService;
import kr.co.ant.study.oop.domain.PaymentInfoVO;
import kr.co.ant.study.oop.service.OopPaymentService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AnswerPaymentController {
	
	
	@Autowired
	private AnswerPaymentService service;
	
	
	@ResponseBody
	@RequestMapping("/hks2/pay/card")
	public String paymentCard(PaymentInfoVO info) throws Exception{
		service.paymentCard(info);
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/hks2/pay/mobile")
	public String paymentMobile(PaymentInfoVO info) throws Exception{
		service.paymentMobile(info);
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping("/hks2/pay/bank")
	public String paymentBank(PaymentInfoVO info) throws Exception{
		service.paymentBank(info);
		return "ok";
	}
}
