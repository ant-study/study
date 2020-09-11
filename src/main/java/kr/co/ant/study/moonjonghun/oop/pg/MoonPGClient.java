package kr.co.ant.study.moonjonghun.oop.pg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MoonPGClient {

	@Autowired
	ObjectMapper mapper;

	public String doPayment(String json) {
		try {
			//json형태의 데이터를 Object로 변환하고 또 그 값을 String 타입으로 변환한다.
			String s = mapper.writeValueAsString(mapper.readValue(json, Object.class));
			System.out.println(s);
			return "";
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
