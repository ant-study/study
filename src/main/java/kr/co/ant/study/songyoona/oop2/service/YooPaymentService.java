/**
 * Author : yooS
 * Create Date : 2020. 9. 15.
 */
package kr.co.ant.study.songyoona.oop2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.ant.study.hankwangsu.oop.payment.AnswerCardPayment;
import kr.co.ant.study.songyoona.oop2.domain.PaymentInfo;
import kr.co.ant.study.songyoona.oop2.payment.BankAccountPayment;
import kr.co.ant.study.songyoona.oop2.payment.CardPayment;
import kr.co.ant.study.songyoona.oop2.payment.MobilePayment;
import kr.co.ant.study.songyoona.oop2.pg.ANTPaymentResponse;
import kr.co.ant.study.songyoona.oop2.validate.FixedLengthValidator;
import kr.co.ant.study.songyoona.oop2.validate.MinLengthValidator;
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

    // 하나로 합쳐보자
    public void paymentTotal(PaymentInfo info) throws Exception {


    }
}
