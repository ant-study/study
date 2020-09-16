package kr.co.ant.study.imsoyeon.d_oop.service;

import org.springframework.stereotype.Component;

import kr.co.ant.study.imsoyeon.d_oop.payment.Payment;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class YPaymentFacade {

	/**
	 * 공통작업 처리
	 * 공통으로 처리하는 작업은 내가 다 함
	 * @param p
	 */
	public void doPayment(Payment p) {
		//Validate 체크 => 오류인경우 예외 처리
		//Logging
		//PG사로 보낼 Data 변환 => 만약 공통으로 변환하기 어렵다면 변환해서 넘겨줘 그럼 변환한 값만 가져와서 Json으로 다시 변환 할께
		//변환된값 Json을 다시 변환
		//PG 호출
		//결과 처리
	}
}
