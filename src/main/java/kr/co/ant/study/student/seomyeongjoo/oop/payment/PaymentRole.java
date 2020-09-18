package kr.co.ant.study.student.seomyeongjoo.oop.payment;

import kr.co.ant.study.student.seomyeongjoo.oop.domain.ResultVO;

//1. 유효성 검사
//2. Json 이 아닌 VO 로 변환
//3. 결제 전 데이터 확인
//4. 오류인지 확인
//5. Return Data
//-> 4/5 번 함께 로그로 보여줌
public interface PaymentRole {
    public void validate();
    public ResultVO convertorPaymentInfo();
    public void logging();
    //public retunr Data

}
