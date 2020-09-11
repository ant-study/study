package kr.co.ant.study.oop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.oop.domain.PaySample;
import kr.co.ant.study.oop.pg.ANTPGClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PaymentController {
	
	@Autowired
	ANTPGClient client;
	
	@Autowired
	ObjectMapper mapper;
	
	@ResponseBody
	@RequestMapping("/test")
	public String test(PaySample info) throws JsonProcessingException{
		log.info("PaymentInfo ::: {}", info);
		client.doPayment(mapper.writeValueAsString(info));
		return "ok";
	}
}
