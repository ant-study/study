/**
 * @author crono
 *
 */
package kr.co.ant.study.choijongmin.oop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.choijongmin.oop.vo.PaymentInfo;
import kr.co.ant.study.oop.pg.ANTPGClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PaymentControllerTest {
	@Autowired
	ANTPGClient client;
	
	@Autowired
	ObjectMapper mapper;
	
	@ResponseBody
	@RequestMapping("/paymentControllerTest")
	public String testCard(PaymentInfo info) throws JsonProcessingException{
		log.info("PaymentInfo ::: {}", info);
		client.doPayment(mapper.writeValueAsString(info));
		return "ok";
	}
	
//	@ResponseBody
//	@RequestMapping("/paymentControllerBankTest")
//	public String testBank(PaymentInfo info) throws JsonProcessingException{
//		log.info("PaymentInfo ::: {}", info);
//		client.doPayment(mapper.writeValueAsString(info));
//		return "ok";
//	}
//	
//	@ResponseBody
//	@RequestMapping("/paymentControllerMobileTest")
//	public String testMobile(PaymentInfo info) throws JsonProcessingException{
//		log.info("PaymentInfo ::: {}", info);
//		client.doPayment(mapper.writeValueAsString(info));
//		return "ok";
//	}
}