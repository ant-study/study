/**
 * Author : yooS
 * Create Date : 2020. 9. 16.
 */
package kr.co.ant.study.student.songyoona.oop2.payment;

import org.apache.commons.beanutils.PropertyUtils;

import kr.co.ant.study.student.songyoona.oop2.domain.PaymentInfo;
import kr.co.ant.study.student.songyoona.oop2.pg.vo.ANTPaymentInfo;
import kr.co.ant.study.student.songyoona.oop2.pg.vo.BankPayInfo;
import kr.co.ant.study.student.songyoona.oop2.service.ValidateException;
import kr.co.ant.study.student.songyoona.oop2.validate.YooANTValidator;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 16.
 */
public class BankAccountPayment extends YooAbstracPayment{

    /**
     * @param validator
     * @param vo
     */
    public BankAccountPayment(PaymentInfo vo, YooANTValidator validator) {
        super(vo, validator);
    }


    @Override
    public void validate() throws ValidateException {
        String accountNo = data.getBankPayInfo().getAccountNo();
        // 자리수 이상 validate 추가
        //validator.validate(accountNo.length(), 15);
        if(!validator.validate(accountNo.length(), 15))
            throw new ValidateException(data.getPaymentType()+"입력값:"+accountNo.length()+" /15 more");
    }

    @Override
    public ANTPaymentInfo convertToANTPaymentInfo() throws Exception{
        ANTPaymentInfo antPaymentInfo = super.convertToANTPaymentInfo();

        BankPayInfo bankInfo = new BankPayInfo();
        PropertyUtils.copyProperties(bankInfo, data.getBankPayInfo());
        antPaymentInfo.setPayInfo(bankInfo);

        return antPaymentInfo;

    }

    @Override
    public void logging() {
        super.logging();
        System.out.println("Bank logging");
    }

}
