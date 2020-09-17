package kr.co.ant.study.imsoyeon.d_oop.pg.vo;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import kr.co.ant.study.imsoyeon.d_oop.domain.RequestPayInfo;
import kr.co.ant.study.imsoyeon.d_oop.payment.AbstractPayment;
import kr.co.ant.study.imsoyeon.d_oop.payment.Payment;
import kr.co.ant.study.imsoyeon.d_oop.validate.FixLengthValidatorY;
import kr.co.ant.study.imsoyeon.d_oop.validate.MinLengthValidatorY;
import kr.co.ant.study.imsoyeon.d_oop.validate.PGValidatorY;

/**
 * 결제를 한다.
 * 결제수단을 선택한다.
 * 결제해! 카드야.
 * convertToPaymentVO할 때 카드냐 모바일이냐 계좌냐가 필요하지? 그에 따라서 되돌려주는 객체 바꿔주면되지?
 * */
public class PaymentFactoryY {
	
	private static Map<String, Object> map = new HashMap<String, Object>();
	
	static {
		map.put("CARD", new FixLengthValidatorY());
		map.put("ACCOUNT", new MinLengthValidatorY());
		map.put("MOBILE", new FixLengthValidatorY());
	}
	
	/**
	 * <pre>
	 * Comment : PGPaymentDetailsY Factory
	 * </pre>
	 * @param type
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static Payment specifyPayment(RequestPayInfo inputVO) throws Exception {
		
		Class clazz = null;
		
		for (PaymentTypeEnum classType : PaymentTypeEnum.values()) {
			if (classType.name().equals(inputVO.getType())) {
				clazz = classType.getPayment();
			}
		}		
		Constructor constructor = clazz.getConstructor(RequestPayInfo.class, PGValidatorY.class);
		
		return (Payment) constructor.newInstance(inputVO, map.get(inputVO.getType()));
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
