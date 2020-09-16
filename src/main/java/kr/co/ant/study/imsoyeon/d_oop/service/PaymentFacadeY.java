package kr.co.ant.study.imsoyeon.d_oop.service;

import org.springframework.stereotype.Component;

import kr.co.ant.study.imsoyeon.d_oop.payment.Payment;
import kr.co.ant.study.imsoyeon.d_oop.pg.vo.PGPaymentInfo;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PaymentFacadeY {

	/**
	 * 공통작업 처리
	 * 공통으로 처리하는 작업은 내가 다 함
	 * @param payment
	 */
	public void doPayment(Payment payment) throws Exception {
		/*
		payment.convertToPaymentVO(payment, inputVO);
		
		PaymentFacadeY에서 inputVO가지고 컨버트하려면, 여기에 또 inputVO 가져와야해 
		Payment 호출 용 메서드를 하나 만들면 될것같음		
		 */
		PGPaymentInfo vo = payment.convertToPaymentVO();
		log.info("VO : {}", vo.toString());
		
//		payment.validate();
		
		//Validate 체크 => 오류인경우 예외 처리
		//Logging
		//PG사로 보낼 Data 변환 => 만약 공통으로 변환하기 어렵다면 변환해서 넘겨줘 그럼 변환한 값만 가져와서 Json으로 다시 변환 할께
		//변환된값 Json을 다시 변환
		//PG 호출
		//결과 처리
	}
}
