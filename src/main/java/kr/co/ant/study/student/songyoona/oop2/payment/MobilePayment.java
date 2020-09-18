/**
 * Author : yooS
 * Create Date : 2020. 9. 16.
 */
package kr.co.ant.study.student.songyoona.oop2.payment;

import org.apache.commons.beanutils.PropertyUtils;

import kr.co.ant.study.student.songyoona.oop2.domain.PaymentInfo;
import kr.co.ant.study.student.songyoona.oop2.pg.vo.ANTPaymentInfo;
import kr.co.ant.study.student.songyoona.oop2.pg.vo.MobilePayInfo;
import kr.co.ant.study.student.songyoona.oop2.service.ValidateException;
import kr.co.ant.study.student.songyoona.oop2.validate.YooANTValidator;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 16.
 */
public class MobilePayment extends YooAbstracPayment{

    public MobilePayment(PaymentInfo vo, YooANTValidator validator) {
        super(vo, validator);
    }

    @Override
    public void validate() throws ValidateException {
        String mobileNo = data.getMobilePayInfo().getMobileNo();

        if(!validator.validate(mobileNo.length(), 16))
            throw new ValidateException(data.getPaymentType()+"입력값:"+mobileNo.length()+" /16 fix");
    }

    @Override
    public ANTPaymentInfo convertToANTPaymentInfo() throws Exception {
        ANTPaymentInfo antPaymentInfo = super.convertToANTPaymentInfo();

        MobilePayInfo mobileInfo = new MobilePayInfo();
        PropertyUtils.copyProperties(mobileInfo, data.getMobilePayInfo());

        antPaymentInfo.setPayInfo(mobileInfo);

        return antPaymentInfo;
    }

    @Override
    public void logging() {
        super.logging();
        System.out.println("mobile logging");
    }
}
