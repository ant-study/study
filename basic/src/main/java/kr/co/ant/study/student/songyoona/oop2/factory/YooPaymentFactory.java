/**
 * Author : yooS
 * Create Date : 2020. 9. 17.
 */
package kr.co.ant.study.student.songyoona.oop2.factory;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;

import org.springframework.stereotype.Service;

import kr.co.ant.study.student.songyoona.lamda.ex2.FValidator;
import kr.co.ant.study.student.songyoona.oop2.domain.PaymentInfo;
import kr.co.ant.study.student.songyoona.oop2.payment.BankAccountPayment;
import kr.co.ant.study.student.songyoona.oop2.payment.CardPayment;
import kr.co.ant.study.student.songyoona.oop2.payment.MobilePayment;
import kr.co.ant.study.student.songyoona.oop2.payment.YooPayment;
import kr.co.ant.study.student.songyoona.oop2.validate.YooANTValidator;



/**
 * @description :
 * @author : yooS
 * @param <T>
 * @createDate : 2020. 9. 17.
 */
@Service
public class YooPaymentFactory {

    // validation Map
    private static Map<String, Object> validateMap = new HashMap<String, Object>();

    // lamda 함수 셋팅
    public static BiPredicate<Integer, Integer> fixed = (l, t) -> l == t;
    public static BiPredicate<Integer, Integer> min = (ll, tt) -> ll >= tt;

    static {
        // 해당 validator 적용방식
//        validateMap.put("CARD", new FixedLengthValidator());
//        validateMap.put("BANK", new MinLengthValidator());
//        validateMap.put("MOBILE", new FixedLengthValidator());

        // lamda 방식
        validateMap.put("CARD", new FValidator(fixed));
        validateMap.put("BANK", new FValidator(min));
        validateMap.put("MOBILE", new FValidator(fixed));
    }

    // payment type Map
//    private static Map<String, Object> paymentMap = new HashMap<String, Object>();
//    static {
//        paymentMap.put("CARD", new CardPayment(null, null));
//        paymentMap.put("BANK", new BankAccountPayment(null, null));
//        paymentMap.put("MOBILE", new MobilePayment(null, null));
//    }

    private static Map<String, Class<? extends YooPayment>> paymentMap = new HashMap<String, Class<? extends YooPayment>>();
    static {
        paymentMap.put("CARD", CardPayment.class);
        paymentMap.put("BANK", BankAccountPayment.class);
        paymentMap.put("MOBILE", MobilePayment.class);
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
        Class<? extends YooPayment> clazz = paymentMap.get(type);

        // payment 객체 생성
        Constructor<? extends YooPayment> constructor = clazz.getConstructor(PaymentInfo.class, YooANTValidator.class);

        // 해당 payment validate set
        payInfo = constructor.newInstance(info, selectValidate(type));

        return payInfo;
    }





    // 람다식 유효성체크 방식
//    public YooPayment selectPayInfo(PaymentInfo info, FValidator val) throws Exception {
//        YooPayment payInfo = null;
//        String type = info.getPaymentType();
//
//        // payment 선택
//        Class<? extends YooPayment> clazz = paymentMap.get(type);
//
//        // payment 객체 생성
//        Constructor<? extends YooPayment> constructor = clazz.getConstructor(PaymentInfo.class, YooANTValidator.class);
//
//        // 해당 payment validate set
//        payInfo = constructor.newInstance(info, val);
//        // 람다validate 한개로..?
//        return payInfo;
//    }
}
