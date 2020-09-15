package kr.co.ant.study.moonjonghun.oop.pg;


import org.springframework.stereotype.Component;

import kr.co.ant.study.moonjonghun.oop.domain.MoonPaymentVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MoonPGClient {
	

	public <T extends MoonPaymentVO> Object doPayment(String json, T obj) throws Exception{
		
		
		
		return null;
	}
	
}
