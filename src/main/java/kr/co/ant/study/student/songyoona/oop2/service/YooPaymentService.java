/**
 * Author : yooS
 * Create Date : 2020. 9. 15.
 */
package kr.co.ant.study.student.songyoona.oop2.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.student.songyoona.oop2.domain.PaymentInfo;
import kr.co.ant.study.student.songyoona.oop2.factory.YooPaymentFactory;
import kr.co.ant.study.student.songyoona.oop2.payment.BankAccountPayment;
import kr.co.ant.study.student.songyoona.oop2.payment.CardPayment;
import kr.co.ant.study.student.songyoona.oop2.payment.MobilePayment;
import kr.co.ant.study.student.songyoona.oop2.payment.YooPayment;
import kr.co.ant.study.student.songyoona.oop2.pg.ANTPaymentResponse;
import kr.co.ant.study.student.songyoona.oop2.validate.FixedLengthValidator;
import kr.co.ant.study.student.songyoona.oop2.validate.MinLengthValidator;
import lombok.extern.slf4j.Slf4j;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 15.
 */
@Slf4j
@Service
public class YooPaymentService {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    YooPaymentFacade facade;

    @Autowired
    YooPaymentFactory factory;


    public void paymentCard(PaymentInfo info) throws Exception{
        CardPayment cardPayment = new CardPayment(info, new FixedLengthValidator());

        ANTPaymentResponse response = facade.doPayment(cardPayment);
    }


    public void paymentBank(PaymentInfo info) throws Exception{
        BankAccountPayment bankPayment = new BankAccountPayment(info, new MinLengthValidator());

        ANTPaymentResponse response = facade.doPayment(bankPayment);
    }


    public void paymentMobile(PaymentInfo info) throws Exception{
        MobilePayment mobilePayment = new MobilePayment(info, new FixedLengthValidator());

        ANTPaymentResponse response = facade.doPayment(mobilePayment);
    }


    /**
     * 한 메소드에서 PaymentType에 따라 여러가지 결제수단 처리
     * 아래 로직을 Factory Pattern을 참고 해서
     * Factory Class를 만듭시다.
     * @param infoVO
     * @throws Exception
     */
    public void compositePayment(PaymentInfo info) throws Exception {

        //YooPayment selectPayment = selectPayInfo(info);
        YooPayment selectPayment = factory.selectPayInfo(info);


        ANTPaymentResponse response = facade.doPayment(selectPayment);
    }





    /**
     * PaymentType에 따라 여러가지 결제수단 선택해서 return => CLASS로 빼기
     * @param PaymentInfo
     */
    public YooPayment selectPayInfo(PaymentInfo info) {
        YooPayment payInfo = null;
        String type = info.getPaymentType();

        HashMap<String, Object> payTypes = new HashMap<String , Object>();
        //if(type.equals("CARD"))
            payTypes.put(type, new CardPayment(info, new FixedLengthValidator()));
        //else if(type.equals("BANK"))
            payTypes.put(type, new BankAccountPayment(info, new MinLengthValidator()));
        //else if(type.equals("MOBILE"))
            payTypes.put(type, new MobilePayment(info, new FixedLengthValidator()));


        payInfo = (YooPayment)payTypes.get(type);
        return payInfo;
    }
}
