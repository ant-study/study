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

import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;

@Service
public class JuuPaymentService {

    @Autowired
    JuuANTPGClient client;

    @Autowired
    ObjectMapper mapper;

    public void cardPay(PaymentInfo info) throws Exception {

        CardInfo cardInfo = info.getCardInfo();

        if(validateCheck(cardInfo.getCardNo(), 16)){
            client.doPayment(mapper.writeValueAsString(info));
        }else{
            throw new Exception("Fail");
        }
    }

    public void mobilePay(PaymentInfo info) throws Exception{
        MobileInfo mobileInfo = info.getMobileInfo();

        if(validateCheck(mobileInfo.getMobileNo(), 10)){
            client.doPayment(mapper.writeValueAsString(info));
        }else{
            throw new Exception("Fail");
        }
    }

    public void bankPay(PaymentInfo info) throws Exception{
        BankAccountInfo bankAccountInfo = info.getBankAccountInfo();

        if(validateCheck(bankAccountInfo.getAccountNo(), 12)){
            client.doPayment(mapper.writeValueAsString(info));
        }else{
            throw new Exception("Fail");
        }
    }

//    private <T extends Validate>boolean validate(T t){
//        return t.validate();
//    }

    public boolean validateCheck(String str, int limit){
        int t = str.length();
        BiPredicate<Integer, Integer> validateCheck = (i, j) -> i == j;
        return validateCheck.test(t, limit);
    }

}
