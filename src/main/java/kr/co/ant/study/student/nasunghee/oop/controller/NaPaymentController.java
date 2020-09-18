package kr.co.ant.study.student.nasunghee.oop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.oop.pg.ANTPGClient;
import kr.co.ant.study.student.nasunghee.oop.payment.PayBaseInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NaPaymentController {
	
	@Autowired
	ANTPGClient client;
	
	@Autowired
	ObjectMapper mapper;
	
	/*
	 * 공통 : product_id / product_name / amount / payment_type(CARD , MOBILE , BANK) > 공통vo ,  string으로 , 부모
	 * 카드 : card_info - card_no / card_code / expire_date > extends 공통
	 * 휴대폰 : mobile_info - mobile_no / user_name / birthday
	 * 계좌이체 : bank_account_info - account_no / bank_code / account_pw
	 * 
	 * payment_type과 자식객체의 key값과 매칭을 어떻게 할까 
	 *    ---> ENUM? ---> ENUM Class는 따로 부모객체 안에? 아니면 따로? 
	 *    ---> 이건 또 자식 객체랑 어떻게 연결하지?
	 *    
	 *    비즈메카 참조
	 */
	@ResponseBody
	@RequestMapping("/sh/test")
	public String test(PayBaseInfo info) throws JsonProcessingException{
		log.info("PaymentInfo ::: {}", info);
		client.doPayment(mapper.writeValueAsString(info));
		return "ok";
	}
}
