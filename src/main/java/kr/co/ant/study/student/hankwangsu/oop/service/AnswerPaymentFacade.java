package kr.co.ant.study.student.hankwangsu.oop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;

import kr.co.ant.study.oop.service.PaymentException;
import kr.co.ant.study.oop.service.ValidateException;
import kr.co.ant.study.student.hankwangsu.oop.payment.Payment;
import kr.co.ant.study.student.hankwangsu.oop.pg.AnswerANTPGClient;
import kr.co.ant.study.student.hankwangsu.oop.pg.vo.ANTPayInfo;
import kr.co.ant.study.student.hankwangsu.oop.pg.vo.ANTPaymentInfo;
import kr.co.ant.study.student.hankwangsu.oop.pg.vo.ANTPaymentResponse;
import lombok.extern.slf4j.Slf4j;
/**
 * Facade Pattern 
 * 영화를 보려고 팝콘 준비하고, 콜라도 준비하고, 티비에 넷플릭스 연결해두고 영화를 고른다 과정이 있으면,
 * 저 과정을 한곳에 두고 하나하나 호출하면 → 절차 지향
 * 서브 클래스 여러개에 나눠서 두고 하나로 묶어서 서브 클래스를 사용하고 싶을 때 Facade Pattern 활용
 * 	ex) 
 * 		service에서 facade.doPayment() 호출
 * 
 * */
@Component
@Slf4j
public class AnswerPaymentFacade {

	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	AnswerANTPGClient client;
	/**
	 * 공통작업 처리
	 * 공통으로 처리하는 작업은 내가 다 함
	 * @param p
	 * @throws Exception 
	 */
	public ANTPaymentResponse doPayment(Payment p) throws Exception {
		//Validate 체크 => 오류인경우 예외 처리
		try {
			p.validate();
		}catch(ValidateException e) {
			log.error("유효성 검사 오류입니다. {}", e.getMessage());
			throw e;
		}
		
		//Logging
		p.logging();
		
		//PG사로 보낼 Data 변환 => 만약 공통으로 변환하기 어렵다면 변환해서 넘겨줘 그럼 변환한 값만 가져와서 Json으로 다시 변환 할께
		ANTPaymentInfo info = p.convertToANTPaymentInfo();
		
		//변환된값 Json을 다시 변환
		String json = toJson(info);
		
		//PG 호출
		String result =  client.doPayment(json);
		
		//결과 처리
		ANTPaymentResponse  response = mapper.readValue(result, ANTPaymentResponse.class);
		
		if(response.isSuccess()) {
			return response;
		}else {
			throw new PaymentException(response.getMessage()); 
		}
	}
	
	public String toJson(ANTPaymentInfo paymentInfo) throws Exception {
		ANTPayInfo payInfo = paymentInfo.getPayInfo();
		
		String subName = payInfo.getClass().getSimpleName();
		String snakeName = ((SnakeCaseStrategy)PropertyNamingStrategy.SNAKE_CASE).translate(subName);
		
		ObjectNode mainNode = (ObjectNode) mapper.valueToTree(paymentInfo);
		mainNode.putPOJO(snakeName, payInfo);
		
		return mainNode.toString();
	}
}
