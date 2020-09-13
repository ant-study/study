package kr.co.ant.study.vvooss.tran.common;
import kr.co.ant.study.vvooss.tran.vo.*;

public enum PaymentType {

	CARD("신용카드","CardInfo",CardInfo.class),
	MOBILE("핸드폰","MobileInfo",MobileInfo.class), 
	BANK("계좌이체","BankAccountInfo",BankAccountInfo.class);
	
	private String paymentKorNm;
	private String classNm;
	private Class<?>  clazz;
	
	private PaymentType(String value,String classNm, Class<?> clazz) {
		this.paymentKorNm = value;
		this.classNm = classNm;
		this.clazz = clazz;
	}
	public String getPaymentKorNm() {
		return paymentKorNm;
	}
	public Class<?> getClazz() {
		return clazz;
	}
	public String getClassNm() {
		return classNm;
	}
	public <T extends SettleInfo> T getObj() throws Exception {
		
		return (T) clazz.newInstance();
	}

}
