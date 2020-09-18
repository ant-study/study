/**
 * Author : yooS
 * Create Date : 2020. 9. 17.
 */
package kr.co.ant.study.songyoona.oop2.factory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.co.ant.study.songyoona.oop2.domain.PaymentInfo;
import kr.co.ant.study.songyoona.oop2.payment.BankAccountPayment;
import kr.co.ant.study.songyoona.oop2.payment.CardPayment;
import kr.co.ant.study.songyoona.oop2.payment.MobilePayment;
import kr.co.ant.study.songyoona.oop2.payment.YooPayment;
import kr.co.ant.study.songyoona.oop2.validate.FixedLengthValidator;
import kr.co.ant.study.songyoona.oop2.validate.MinLengthValidator;
import kr.co.ant.study.songyoona.oop2.validate.YooANTValidator;



/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 17.
 */
@Service
public class YooPaymentFactory {

    // validation Map
    private static Map<String, Object> validateMap = new HashMap<String, Object>();
    static {
        validateMap.put("CARD", new FixedLengthValidator());
        validateMap.put("BANK", new MinLengthValidator());
        validateMap.put("MOBILE", new FixedLengthValidator());
    }

    // payment type Map
    private static Map<String, Object> paymentMap = new HashMap<String, Object>();
    static {
        paymentMap.put("CARD", new CardPayment(null, null));
        paymentMap.put("BANK", new BankAccountPayment(null, null));
        paymentMap.put("MOBILE", new MobilePayment(null, null));
    }

    // payment에 따른 validation get
    public YooANTValidator selectValidate(String type) {
        return (YooANTValidator) validateMap.get(type);
    }

    // 해당  payment 객체 setting return
    public YooPayment selectPayInfo(PaymentInfo info) throws Exception {
        YooPayment payInfo = null;
        String type = info.getPaymentType();

        // payment 선택
        Class clazz = paymentMap.get(type).getClass();
        // payment 객체 생성
        Constructor constructor = clazz.getConstructor(PaymentInfo.class, YooANTValidator.class);

        // 해당 payment validate set
        payInfo = (YooPayment) constructor.newInstance(info, selectValidate(type));

        return payInfo;
    }
}