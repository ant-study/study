package kr.co.ant.study.imsoyeon.d_oop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.ant.study.imsoyeon.d_oop.pg.vo.AccountInfoVO;
import kr.co.ant.study.imsoyeon.d_oop.pg.vo.CardInfoVO;
import kr.co.ant.study.imsoyeon.d_oop.pg.vo.MobileInfoVO;
import kr.co.ant.study.imsoyeon.d_oop.service.YPaymentService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class PaymentControllerY {
	
	@Autowired
	private YPaymentService service;

	@ResponseBody
	@RequestMapping(value = "/payCard")
	public String payCard(CardInfoVO request) throws Exception {
		
		service.doCardPay(request);
		
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping(value = "/payAccount")
	public String payAccount(AccountInfoVO request) throws Exception {
		
		service.doAccountPay(request);
		
		return "ok";
	}
	
	@ResponseBody
	@RequestMapping(value = "/payMobile")
	public String payMobile(MobileInfoVO request) throws Exception {
		
		service.doMobilePay(request);
		
		return "ok";
	}
}
