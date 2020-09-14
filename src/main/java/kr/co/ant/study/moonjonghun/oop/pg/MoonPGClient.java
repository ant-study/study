package kr.co.ant.study.moonjonghun.oop.pg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MoonPGClient {
	
	@Autowired
	ObjectMapper mapper;

	public Object doPayment(Object obj) {
		
		return null;
	}
}
