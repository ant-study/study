/**
 * Author : jmChoi
 * Create Date : 2020. 9. 15.
 */
/**
 * @description : 
 * @author : jmChoi
 * @createDate : 2020. 9. 15.
 */
package kr.co.ant.study.choijongmin.oop.validate;

import kr.co.ant.study.choijongmin.oop.vo.PaymentInfo;
import kr.co.ant.study.choijongmin.oop.vo.PgPaymentInfo;
import kr.co.ant.study.oop.service.ValidateException;

public interface PaymentStrategy {
	/** 
	 * 1. validate() 결제 수단 별 자릿 수 체크
	 * 2. 요청 Data 변환 (Json아님) 화면에서 넘어온 VO를 PG사로 넘겨야 할 VO로 변환
	 * 3. 결제 후 오류인지 아닌지 판단
	 **/
	public void validate() throws ValidateException;
	
	public PgPaymentInfo paymentConvert(PaymentInfo info);
	
}