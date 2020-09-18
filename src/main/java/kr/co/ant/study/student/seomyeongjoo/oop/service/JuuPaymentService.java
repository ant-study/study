package kr.co.ant.study.student.seomyeongjoo.oop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.ant.study.student.seomyeongjoo.oop.domain.BankAccountInfo;
import kr.co.ant.study.student.seomyeongjoo.oop.domain.CardInfo;
import kr.co.ant.study.student.seomyeongjoo.oop.domain.MobileInfo;
import kr.co.ant.study.student.seomyeongjoo.oop.domain.PaymentInfo;
import kr.co.ant.study.student.seomyeongjoo.oop.pg.JuuANTPGClient;
import kr.co.ant.study.student.seomyeongjoo.oop.util.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JuuPaymentService {

    @Autowired
    JuuANTPGClient client;

    @Autowired
    ObjectMapper mapper;

    public void cardPay(PaymentInfo info) throws Exception {

        CardInfo cardInfo = info.getCardInfo();

        cardInfo.setName("카드");
        cardInfo.setSize(16);

        if(this.validate(cardInfo)){
            client.doPayment(mapper.writeValueAsString(info));
        }else{
            throw new Exception("Fail");
        }
    }

    public void mobilePay(PaymentInfo info) throws Exception{
        MobileInfo mobileInfo = info.getMobileInfo();

        mobileInfo.setName("휴대");
        mobileInfo.setSize(10);

        if(this.validate(mobileInfo)){
            client.doPayment(mapper.writeValueAsString(info));
        }else{
            throw new Exception("Fail");
        }
    }

    public void bankPay(PaymentInfo info) throws Exception{
        BankAccountInfo bankAccountInfo = info.getBankAccountInfo();

        bankAccountInfo.setName("계좌");
        bankAccountInfo.setSize(12);

        if(this.validate(bankAccountInfo)){
            client.doPayment(mapper.writeValueAsString(info));
        }else{
            throw new Exception("Fail");
        }
    }

    private <T extends Validate>boolean validate(T t){
        return t.validate();
    }
}
