package kr.co.ant.study.student.imsoyeon.d_oop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;

import kr.co.ant.study.student.imsoyeon.d_oop.payment.Payment;
import kr.co.ant.study.student.imsoyeon.d_oop.pg.ANTPGClientY;
import kr.co.ant.study.student.imsoyeon.d_oop.pg.response.ResponseErrorTestY;
import kr.co.ant.study.student.imsoyeon.d_oop.pg.response.PGPaymentResponseSampleY;
import kr.co.ant.study.student.imsoyeon.d_oop.pg.vo.PGPaymentDetailsY;
import kr.co.ant.study.student.imsoyeon.d_oop.pg.vo.PGPaymentInfo;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PaymentFacadeY {
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	ANTPGClientY client;

	/**
	 * 공통작업 처리
	 * 공통으로 처리하는 작업은 내가 다 함
	 * @param payment
	 */
	public PGPaymentResponseSampleY doPayment(Payment payment) throws Exception {
		
		payment.validate();
		
		payment.logging();
		
//		3. convert
		/*
		payment.convertToPaymentVO(payment, inputVO);
		
		PaymentFacadeY에서 inputVO가지고 컨버트하려면, 여기에 또 inputVO 가져와야해 
		Payment 호출 용 메서드를 하나 만들면 될것같음		
		 */
		PGPaymentInfo vo = payment.convertToPaymentVO();
		String json = toJson(vo);
		
		String result = client.doPayment(json);		
		
		PGPaymentResponseSampleY response = mapper.readValue(result, PGPaymentResponseSampleY.class);
		
		/* 테스트
		response 처리를 하고 싶은데 try-catch문 쓰고, reponse json값에 따라서 
		로그 찍고 throw new Exception("message")하든지
		에러별로 throw new XXException("message")하든지
		 * */		
		if (response.isSuccess()) {			
			return response;
			
		} else {
			log.error("ERROR CODE ::: {} !!! WHY ::: {}", response.getStatusCode(), response.getError().getWhere()+" "+response.getError().getMessage());
			throw new Exception("ERROR");
		}		
	}
	
	public String toJson(PGPaymentInfo paymentInfo) throws Exception {
		PGPaymentDetailsY payInfo = paymentInfo.getPaymentDetails();
		
		String subName = payInfo.getClass().getSimpleName();
		String snakeName = ((SnakeCaseStrategy)PropertyNamingStrategy.SNAKE_CASE).translate(subName);
		
		ObjectNode mainNode = (ObjectNode) mapper.valueToTree(paymentInfo);
		mainNode.putPOJO(snakeName, payInfo);
		
		return mainNode.toString();
	}

}
