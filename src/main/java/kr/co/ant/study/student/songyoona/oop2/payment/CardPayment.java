/**
 * Author : yooS
 * Create Date : 2020. 9. 16.
 */
package kr.co.ant.study.student.songyoona.oop2.payment;

import org.apache.commons.beanutils.PropertyUtils;

import kr.co.ant.study.student.songyoona.oop2.domain.PaymentInfo;
import kr.co.ant.study.student.songyoona.oop2.pg.vo.ANTPaymentInfo;
import kr.co.ant.study.student.songyoona.oop2.pg.vo.CardPayInfo;
import kr.co.ant.study.student.songyoona.oop2.service.ValidateException;
import kr.co.ant.study.student.songyoona.oop2.validate.YooANTValidator;



/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 16.
 */
public class CardPayment extends YooAbstracPayment{

    public CardPayment(PaymentInfo vo, YooANTValidator validator) {
        super(vo, validator);
    }


    @Override
    public void validate() throws ValidateException {
        // 카드번호 유효성 체크
        String cardNo = data.getCardPayInfo().getCardNo();
        if(!validator.validate(cardNo.length(), 16))
            throw new ValidateException(data.getPaymentType()+"입력값:"+cardNo.length()+" /16 fix");
    }

    // 결제수단별로 convert
    @Override
    public ANTPaymentInfo convertToANTPaymentInfo() throws Exception{
        ANTPaymentInfo paymentInfo = super.convertToANTPaymentInfo();

        CardPayInfo cardInfo = new CardPayInfo();
        PropertyUtils.copyProperties(cardInfo, data.getCardPayInfo());

        paymentInfo.setPayInfo(cardInfo);
        return paymentInfo;
    }

    @Override
    public void logging() {
        super.logging();
        System.out.println("Card logging");
    }
}
