/**
 * Author : yooS
 * Create Date : 2020. 9. 16.
 */
package kr.co.ant.study.student.songyoona.oop2.payment;

import org.apache.commons.beanutils.PropertyUtils;

import kr.co.ant.study.student.songyoona.oop2.domain.PaymentInfo;
import kr.co.ant.study.student.songyoona.oop2.pg.vo.ANTPaymentInfo;
import kr.co.ant.study.student.songyoona.oop2.validate.YooANTValidator;

/**
 * @description : 추상클래스 (인터페이스와 구현체의 역항을 둘다..!)
 * @author : yooS
 * @createDate : 2020. 9. 16.
 */
public abstract class YooAbstracPayment implements YooPayment{

    protected YooANTValidator validator;
    protected PaymentInfo data;

    /**
     * @param validate
     * @param data
     */
    public YooAbstracPayment(PaymentInfo vo, YooANTValidator validator) {
        this.data = vo;
        this.validator = validator;
    }

    @Override
    public ANTPaymentInfo convertToANTPaymentInfo() throws Exception {
        ANTPaymentInfo antPaymentInfo = new ANTPaymentInfo();
        PropertyUtils.copyProperties(antPaymentInfo, data);
        return antPaymentInfo;
    }

    @Override
    public void logging() {
        System.out.println("abstract logging");
    }
}
