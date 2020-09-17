package kr.co.ant.study.imsoyeon.d_oop.pg.vo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * 결제를 한다.
 * 결제수단을 선택한다.
 * 결제해! 카드야.
 * convertToPaymentVO할 때 카드냐 모바일이냐 계좌냐가 필요하지? 그에 따라서 되돌려주는 객체 바꿔주면되지?
 * */
public class PaymentFactoryY {
	
	private static Map<PaymentTypeY, Object> map = new HashMap<PaymentTypeY, Object>();
	
	static {
		map.put(PaymentTypeY.CARD, new PGCardInfo());
//		map.put("ACCOUNT", PGCardInfo:: new);	→ Functional Interface ?
		map.put(PaymentTypeY.ACCOUNT, new PGAccountInfo());
		map.put(PaymentTypeY.MOBILE, new PGMobileInfo());
	}	
	
	/**
	 * <pre>
	 * Comment : PGPaymentDetailsY Factory
	 * </pre>
	 * @param type
	 * @return
	 */
	public Object specifyPayment(PaymentTypeY type) {
//		Supplier라는게 있다는데?
//		return Object......?
		return map.get(type);
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
