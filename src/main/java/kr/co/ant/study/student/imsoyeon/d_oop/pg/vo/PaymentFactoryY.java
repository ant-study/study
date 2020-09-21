package kr.co.ant.study.student.imsoyeon.d_oop.pg.vo;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;

import kr.co.ant.study.student.imsoyeon.d_oop.domain.RequestPayInfo;
import kr.co.ant.study.student.imsoyeon.d_oop.payment.Payment;
import kr.co.ant.study.student.imsoyeon.f_functional.second.BIPredicateValidatorY;
import kr.co.ant.study.student.imsoyeon.f_functional.second.BiPredicateValidatorYImpl;

/**
 * 결제를 한다.
 * 결제수단을 선택한다.
 * 결제해! 카드야.
 * convertToPaymentVO할 때 카드냐 모바일이냐 계좌냐가 필요하지? 그에 따라서 되돌려주는 객체 바꿔주면되지?
 * */
public class PaymentFactoryY {
	
	private static Map<String, Object> map = new HashMap<String, Object>();
	
	static {
//		1.d_oop Test
//		map.put("CARD", new FixLengthValidatorY());
//		map.put("ACCOUNT", new MinLengthValidatorY());
//		map.put("MOBILE", new FixLengthValidatorY());
		
//		2.f_functional Test
		BiPredicate<String, Integer> fixFunc = (v, l) -> v.length() != l;
		BiPredicate<String, Integer> minFunc = (v, l) -> v.length() < l;
		
		map.put("CARD", new BiPredicateValidatorYImpl(fixFunc));
		map.put("ACCOUNT", new BiPredicateValidatorYImpl(minFunc));
		map.put("MOBILE", new BiPredicateValidatorYImpl(fixFunc));
	}
	
	/**
	 * <pre>
	 * Comment : PGPaymentDetailsY Factory
	 * </pre>
	 * @param inputVO
	 * @return
	 * @throws Exception
	 */
	public static Payment createPayment(RequestPayInfo inputVO) throws Exception {		
		
		/*
		for (PaymentTypeEnum classType : PaymentTypeEnum.values()) {
			if (classType.name().equals(inputVO.getType())) {
				clazz = classType.getPayment();
			}
		}
		 * */
		
//		enum class 안에서 <? extends 인터페이스> 
		Class<? extends Payment> clazz = PaymentTypeEnum.valueOf(inputVO.getType()).getPayment();
//		1.d_oop Test
//		Constructor<? extends Payment> constructor = clazz.getConstructor(RequestPayInfo.class, PGValidatorY.class);
		
//		2.f_functional Test
		Constructor<? extends Payment> constructor = clazz.getConstructor(RequestPayInfo.class, BIPredicateValidatorY.class);
		
		return constructor.newInstance(inputVO, map.get(inputVO.getType()));
	}

	/**
	 * <pre>
	 * Comment : factory pattern 테스트용
	 * </pre>
	 * @param type
	 * @return
	 */
	public PGPaymentDetailsY specifyPaymentSimple(String type) {
		PGPaymentDetailsY details = null;
		
		if (type.equals("CARD")) details = new PGCardInfo();
		if (type.equals("ACCOUNT")) details = new PGAccountInfo();
		if (type.equals("MOBILE")) details = new PGMobileInfo();
		
		return details;
	}
	
}
