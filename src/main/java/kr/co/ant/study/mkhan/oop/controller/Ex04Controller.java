package kr.co.ant.study.mkhan.oop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

import kr.co.ant.study.mkhan.oop.vo.BaseInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class Ex04Controller {
	

	@ResponseBody
	@RequestMapping("/mkhan/card")
	public String test(BaseInfo info) throws JsonProcessingException{
		
		//log.info("BaseInfo ::: {}", info);
		
		try {
			
//			client.doPayment(mapper.writeValueAsString(info));
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return "ok";
	}
	
}
