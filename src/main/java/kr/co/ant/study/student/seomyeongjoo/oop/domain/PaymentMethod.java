package kr.co.ant.study.student.seomyeongjoo.oop.domain;


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
