package kr.co.ant.study.imsoyeon.d_oop.vo;

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
//	public void fixLengthValidate();
	
//	public void minLengthValidate();
	
//	2) Request → VO for transfer
	public Object convertVO(RequestPayInfo request) throws Exception;
	
//	3) 결제 후 오류인지 아닌지 판단?
//	public void callPgClient();
	
//	4) 결과 값 Data 변환?
}
