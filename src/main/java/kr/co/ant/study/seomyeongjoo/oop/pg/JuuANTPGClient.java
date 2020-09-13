package kr.co.ant.study.seomyeongjoo.oop.pg;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JuuANTPGClient {
	
	@Autowired
	ObjectMapper mapper;

	public String doPayment(String json) {
		try {
			String s = mapper.writeValueAsString(mapper.readValue(json, Object.class));
			System.out.println(">>>>>>>>>>>>>>>");
			System.out.println(s);
			System.out.println("<<<<<<<<<<<<<<<");
			return "";
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
