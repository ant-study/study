/**
 * @author crono
 *
 */
package kr.co.ant.study.choijongmin.oop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.choijongmin.oop.service.PaymentServiceTest;
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
	
	@Autowired
	PaymentServiceTest paymentServiceTest;
	
	@ResponseBody
	@RequestMapping("/paymentControllerTest")
	public String testCard(PaymentInfo info) throws Exception{
		log.info("PaymentInfo ::: {}", info);
		
		paymentServiceTest.paymentTypeClassification(info);
		
		String jsonData = client.doPayment(mapper.writeValueAsString(info));
		return "ok";
	}
	
//	@ResponseBody
//	@RequestMapping("/paymentControllerBankTest")
//	public String testBank(BankAccountInfo info) throws JsonProcessingException{
//		info.getClass().getDeclaredFields();
//		log.info("PaymentInfo ::: {}", info);
//		String jsonData = client.doPayment(mapper.writeValueAsString(info));
//		return "ok";
//	}
//	
//	@ResponseBody
//	@RequestMapping("/paymentControllerMobileTest")
//	public String testMobile(MobileInfo info) throws JsonProcessingException{
//		info.getClass().getDeclaredFields();
//		log.info("PaymentInfo ::: {}", info);
//		String jsonData = client.doPayment(mapper.writeValueAsString(info));
//		return "ok";
//	}
	
}