package kr.co.ant.study.seomyeongjoo.oop.payment;

import kr.co.ant.study.seomyeongjoo.oop.domain.ResultVO;

public class AbstractPayment implements PaymentRole{
    @Override
    public void validate() {

    }

    @Override
    public ResultVO convertorPaymentInfo() {
        return null;
    }

    @Override
    public void logging() {

    }
}
