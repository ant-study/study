package kr.co.ant.study.seomyeongjoo.oop.domain;

import lombok.Getter;
import lombok.Setter;


public enum PaymentMethod {
    //MOBILE, CARD, BANK;
    MOBILE(MobileInfo.class),
    CARD(CardInfo.class),
    BANK(BankAccountInfo.class);

    private Class paymentMethod;

    PaymentMethod(Class paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Class getPaymentMethod() {
        return paymentMethod;
    }


}
