package kr.co.ant.study.student.imsoyeon.d_oop.pg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.student.imsoyeon.d_oop.pg.response.PGPaymentResponseSampleY;
import kr.co.ant.study.student.imsoyeon.d_oop.pg.response.ResponseErrorTestY;

@Component
public class ANTPGClientY {
	
	@Autowired
	ObjectMapper mapper;

	public String doPayment(String json) {
		try {
//			json → Object
			String s = mapper.writeValueAsString(mapper.readValue(json, Object.class));
			System.out.println(s);
			
//			response test
			return mapper.writeValueAsString(responseTest());
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}	
	
//	테스트용
	public PGPaymentResponseSampleY responseTest() {
		PGPaymentResponseSampleY sample = new PGPaymentResponseSampleY();
		sample.setStatusCode("5XX");
		sample.setError(new ResponseErrorTestY("api server 어딘가에", "문제가 있음"));
//		sample.setStatusCode("4XX");
//		sample.setError(new ResponseErrorTestY("너의 요청 어딘가에", "문제가 있음"));
		
		return sample;		
	}
}
