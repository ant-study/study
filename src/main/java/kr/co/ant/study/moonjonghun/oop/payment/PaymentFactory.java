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
	
	public MoonPaymentVO data;
	public String paymentType;
	public Validation valid;
	
	public PaymentFactory(MoonPaymentVO vo, String type){
		this.data = vo;
		this.paymentType = type;
		if("Mobile".equals(paymentType)) {
			this.valid = new MinlengthValidation();
		}else {
			this.valid = new FixedLengthValidation();
		}
	}
	
	/**
	 * 생성된 payment를 가져오는 메소드
	 * */
	public Payment getPayment() throws Exception{
		
		//1. type을 모두 소문자로 바꾼다.
		//2. capitalize한다.
		//3. enum에서 getCalzz하여 거래타입별 class 정보를 가져온다.
		//4. constructor instance 생성
		//5. 객체를 생성하고 Payment로 upCasting
		
		paymentType = paymentType.toLowerCase();
		String capType = StringUtils.capitalize(paymentType);
		Class clazz = MoonPaymentType.valueOf(capType).getClazz();
		Constructor paymentConst = clazz.getConstructor(MoonPaymentVO.class, Validation.class);
		
		return (Payment) paymentConst.newInstance(data, valid);
		
	}
	
}
