package kr.co.ant.study.choijongmin.oop.service;

import java.lang.reflect.InvocationTargetException;

import org.springframework.stereotype.Component;

import kr.co.ant.study.choijongmin.oop.validate.PaymentStrategy;
import kr.co.ant.study.choijongmin.oop.vo.PaymentInfo;
import kr.co.ant.study.choijongmin.oop.vo.PgPaymentInfo;
import kr.co.ant.study.oop.service.ValidateException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PaymentFacadeTest {

	/**
	 * 공통작업 처리
	 * 공통으로 처리하는 작업은 내가 다 함
	 * @param p
	 * @throws ValidateException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public void doPayment(PaymentStrategy p, PaymentInfo info) throws ValidateException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		//Validate 체크 => 오류인경우 예외 처리
		p.validate();
		
		//PG사로 보낼 Data 변환 => 만약 공통으로 변환하기 어렵다면 변환해서 넘겨줘 그럼 변환한 값만 가져와서 Json으로 다시 변환 할께
		PgPaymentInfo r = p.paymentConvert(info);
		
		//Logging
		String payType = r.getPaymentType();
		Long payAmount = r.getAmount();
		
		log.info("{} 에서 {} 결제 되었습니다.", payType, payAmount);
		
		
		
		//변환된값 Json을 다시 변환
		
		//PG 호출
		
		//결과 처리
		
	}
}
