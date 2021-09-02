/**
 * @author crono
 *
 */
package kr.co.ant.study.student.choijongmin.oop.controller;

import kr.co.ant.study.student.choijongmin.oop.service.PaymentServiceTest;
import kr.co.ant.study.student.choijongmin.oop.vo.PaymentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

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
		paymentServiceTest.paymentTypeClassification(info);
		
		String jsonData = client.doPayment(mapper.writeValueAsString(info));
		return "ok";
	}
	
}