package kr.co.ant.study.imsoyeon.d_oop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;

import kr.co.ant.study.imsoyeon.d_oop.payment.Payment;
import kr.co.ant.study.imsoyeon.d_oop.pg.ANTPGClientY;
import kr.co.ant.study.imsoyeon.d_oop.pg.vo.PGPaymentDetailsY;
import kr.co.ant.study.imsoyeon.d_oop.pg.vo.PGPaymentInfo;
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
	public void doPayment(Payment payment) throws Exception {
		
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
		
//		details card 두개가 생겨..????
		String response = client.doPayment(json);
		
//		5. manager response
		
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
