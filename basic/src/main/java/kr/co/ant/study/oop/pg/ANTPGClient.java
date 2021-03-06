package kr.co.ant.study.oop.pg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ANTPGClient {
	
	@Autowired
	ObjectMapper mapper;

	public String doPayment(String json) {
		try {
			String s = mapper.writeValueAsString(mapper.readValue(json, Object.class));
			System.out.println(s);
			return "";
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
