package kr.co.ant.study.songyoona.oop2.payment;

import kr.co.ant.study.songyoona.oop2.pg.vo.ANTPaymentInfo;
import kr.co.ant.study.songyoona.oop2.service.ValidateException;

/**
 * 결제수단별로 동일한 행위는 여기다가 정의 하자
 * 먼저 행위를 정해야 한다.
 *
 * 1) 유효성 검사
 * 2) 요청 Data 변환 (Json아님) 화면에서 넘어온 VO를 PG사로 넘겨야 할 VO로 변환
 * 3) 결제 후 오류인지 아닌지 판단
 * 4) 결과 값 Data 변환
 */
public interface YooPayment {

    public void validate() throws ValidateException;
    public ANTPaymentInfo convertToANTPaymentInfo() throws Exception;
    public void logging();

}
