package kr.co.ant.study.student.vvooss.tran.common;
import kr.co.ant.study.student.vvooss.tran.vo.BankAccountInfo;
import kr.co.ant.study.student.vvooss.tran.vo.CardInfo;
import kr.co.ant.study.student.vvooss.tran.vo.MobileInfo;
import kr.co.ant.study.student.vvooss.tran.vo.SettleInfo;

public enum PaymentType {

	CARD("신용카드","CardInfo", CardInfo.class),
	MOBILE("핸드폰","MobileInfo", MobileInfo.class),
	BANK("계좌이체","BankAccountInfo", BankAccountInfo.class);
	
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
