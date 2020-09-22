package kr.co.ant.study.student.moonjonghun.oop.payment;

import java.lang.reflect.Constructor;
import java.util.function.BiPredicate;

import org.springframework.util.StringUtils;

import kr.co.ant.study.student.moonjonghun.oop.domain.MoonPaymentType;
import kr.co.ant.study.student.moonjonghun.oop.domain.MoonPaymentVO;
import kr.co.ant.study.student.moonjonghun.oop.domain.Payment;
import kr.co.ant.study.student.moonjonghun.oop.validation.CompositeValidation;
import kr.co.ant.study.student.moonjonghun.oop.validation.Validation;

public class PaymentFactory {
	
	public Payment getPayment(MoonPaymentVO vo, String type) throws Exception{
		
		
		BiPredicate<String, Integer> func = null;
		
		if("MOBILE".equals(type.toUpperCase())) {
			func = (payData, length) -> {return payData.length() > length;};
		} else {
			func = (payData, length) -> {return payData.length() == length;};
		}
		
		
		//1. type을 모두 소문자로 바꾼다.
		//2. capitalize한다.
		//3. enum에서 getCalzz하여 거래타입별 class 정보를 가져온다.
		//4. constructor instance 생성
		//5. 공통 validatation적용을 위해 type param을 넘겨준다.
		
		String sCapType =  StringUtils.capitalize(type.toLowerCase());
		Class<? extends Payment> clazz = MoonPaymentType.valueOf(sCapType).getClazz();
		Constructor<? extends Payment> paymentConst = clazz.getConstructor(MoonPaymentVO.class, Validation.class);
		
		return paymentConst.newInstance(vo, new CompositeValidation(func));
		
	}
	
}
