package kr.co.ant.study.student.moonjonghun.oop.pg;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MoonPGClient {
	
	@Autowired
	ObjectMapper mapper;
	
	public String doPayment(String json, String type) {
		//pg결제 프로세스
//		String s = mapper.writeValueAsString( mapper.readValue(json, Object.class));
		
		return null;
	};
}
