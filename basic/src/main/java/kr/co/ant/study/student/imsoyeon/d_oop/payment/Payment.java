package kr.co.ant.study.student.imsoyeon.d_oop.payment;

import kr.co.ant.study.student.imsoyeon.d_oop.pg.vo.PGPaymentInfo;

/**
 * 결제수단별로 동일한 행위는 여기다가 정의 하자
 * 먼저 행위를 정해야 한다.
 * 
 * 1) 유효성 검사
 * 2) 요청 Data 변환 (Json아님) 화면에서 넘어온 VO를 PG사로 넘겨야 할 VO로 변환
 * 3) 결제 후 오류인지 아닌지 판단
 * 4) 결과 값 Data 변환 
 */
public interface Payment {
	
	/*
	 strategy pattern 참고
	 */
	
//	1) 유효성 검사 : 자릿수 (카드 16 폰 11 계좌 15)
	/**
	 * <pre>
	 * Comment : 전처리 - Validation 수행
	 * </pre>
	 * @throws Exception
	 */
	public void validate() throws Exception;
	
	/**	 
	 * * <pre>
	 * Comment : log
	 * </pre>
	 * @throws Exception
	 */
	public void logging() throws Exception;
	
	/**
	 * <pre>
	 * Comment : 전처리 - Request로 들어온 데이터를 API용 VO로 변환
	 * </pre>
	 * 
	 * @param payment
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public PGPaymentInfo convertToPaymentVO() throws Exception;
	
//	3) 결제 후 오류인지 아닌지 판단? 위에서 하면 되지않아?
	
}
