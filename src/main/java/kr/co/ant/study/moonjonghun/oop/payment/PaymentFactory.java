package kr.co.ant.study.moonjonghun.oop.payment;

import java.lang.reflect.Constructor;

import org.springframework.util.StringUtils;

import kr.co.ant.study.moonjonghun.oop.domain.MoonPaymentType;
import kr.co.ant.study.moonjonghun.oop.domain.MoonPaymentVO;
import kr.co.ant.study.moonjonghun.oop.domain.Payment;
import kr.co.ant.study.moonjonghun.oop.validation.FixedLengthValidation;
import kr.co.ant.study.moonjonghun.oop.validation.MinlengthValidation;
import kr.co.ant.study.moonjonghun.oop.validation.Validation;

public class PaymentFactory {
	
	public Payment getPayment(MoonPaymentVO vo, String type) throws Exception{
		
		Validation valid = null;
		
		if("MOBILE".equals(type.toUpperCase())) {
			valid = new MinlengthValidation();
		}else {
			valid = new FixedLengthValidation();
		}
		
		//1. type을 모두 소문자로 바꾼다.
		//2. capitalize한다.
		//3. enum에서 getCalzz하여 거래타입별 class 정보를 가져온다.
		//4. constructor instance 생성
		
		String sCapType =  StringUtils.capitalize(type.toLowerCase());
		Class<? extends Payment> clazz = MoonPaymentType.valueOf(sCapType).getClazz();
		Constructor<? extends Payment> paymentConst = clazz.getConstructor(MoonPaymentVO.class, Validation.class);
		
		return paymentConst.newInstance(vo, valid);
		
	}
	
}
