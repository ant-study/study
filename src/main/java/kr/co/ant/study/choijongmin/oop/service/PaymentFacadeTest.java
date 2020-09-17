package kr.co.ant.study.choijongmin.oop.service;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.choijongmin.oop.validate.PaymentStrategy;
import kr.co.ant.study.choijongmin.oop.vo.PaymentInfo;
import kr.co.ant.study.choijongmin.oop.vo.PgPaymentInfo;
import kr.co.ant.study.oop.pg.ANTPGClient;
import kr.co.ant.study.oop.service.ValidateException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PaymentFacadeTest {
	
	@Autowired
	ANTPGClient client;
	
	@Autowired
	ObjectMapper mapper;

	/**
	 * 공통작업 처리
	 * 공통으로 처리하는 작업은 내가 다 함
	 * @param p
	 * @throws ValidateException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws JsonProcessingException 
	 */
	public void doPayment(PaymentStrategy p, PgPaymentInfo r) throws ValidateException, JsonProcessingException {
		//Validate 체크 => 오류인경우 예외 처리
		p.validate();
		
		//PG사로 보낼 Data 변환 => 만약 공통으로 변환하기 어렵다면 변환해서 넘겨줘 그럼 변환한 값만 가져와서 Json으로 다시 변환 할께
		
		//Logging
		String payType = r.getPaymentType();
		Long payAmount = r.getAmount();
		
		DecimalFormat formatter = new DecimalFormat("###,###");
		String wonAmount = formatter.format(payAmount);
		log.info("{} 에서 {} 원 결제 되었습니다.", payType, wonAmount);
		
		//변환된값 Json을 다시 변환
		
		//PG 호출
		String jsonData = client.doPayment(mapper.writeValueAsString(r));
		
		//결과 처리
		
	}
}
