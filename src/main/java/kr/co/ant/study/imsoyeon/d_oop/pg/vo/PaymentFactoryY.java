package kr.co.ant.study.imsoyeon.d_oop.pg.vo;

import java.util.HashMap;
import java.util.Map;

import kr.co.ant.study.imsoyeon.d_oop.domain.RequestPayInfo;
import kr.co.ant.study.imsoyeon.d_oop.payment.CardPayment;
import kr.co.ant.study.imsoyeon.d_oop.payment.Payment;
import kr.co.ant.study.imsoyeon.d_oop.validate.FixLengthValidatorY;
import kr.co.ant.study.imsoyeon.d_oop.validate.MinLengthValidatorY;

/**
 * 결제를 한다.
 * 결제수단을 선택한다.
 * 결제해! 카드야.
 * convertToPaymentVO할 때 카드냐 모바일이냐 계좌냐가 필요하지? 그에 따라서 되돌려주는 객체 바꿔주면되지?
 * */
public class PaymentFactoryY {
	
	private static Map<String, Object> map = new HashMap<String, Object>();
	
	static {
//		이렇게 하면 안돼. inputInfo가 null로 들어가잖아
//		map.put("CARD", PaymentTypeY.CARD);
//		map.put("ACCOUNT", PaymentTypeY.ACCOUNT);
//		map.put("MOBILE", PaymentTypeY.MOBILE);
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
		
		 map.put("CARD", new CardPayment(inputVO, new FixLengthValidatorY()));
		 map.put("ACCOUNT", new CardPayment(inputVO, new MinLengthValidatorY()));
		 map.put("MOBILE", new CardPayment(inputVO, new FixLengthValidatorY()));
		return (Payment) map.get(inputVO.getType());
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
