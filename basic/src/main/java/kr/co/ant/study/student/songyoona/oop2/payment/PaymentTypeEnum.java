/**
 * Author : yooS
 * Create Date : 2020. 9. 16.
 */
package kr.co.ant.study.student.songyoona.oop2.payment;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 16.
 */
public enum PaymentTypeEnum {

    CARD(CardPayment.class),
    MOBILE(MobilePayment.class),
    BANK(BankAccountPayment.class);

    private Class<?> infoClass;

    private PaymentTypeEnum(Class<?> infoType) {
        this.infoClass = infoType;
    }

    public Class<?> getInfoClass() {
        return infoClass;
    }


}
