/**
 * Author : yooS
 * Create Date : 2020. 9. 13.
 */
package kr.co.ant.study.student.songyoona.oop.vo;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 13.
 */
public enum YooPaySubModule {
    CARD("card",CardInfo.class),
    BANK("bank",BankInfo.class),
    Mobile("mobile",MobileInfo.class);

//    private Class value;
//
//    /**
//     * @param value
//     */
//    private YooPaySubModule(Class value) {
//        this.value = value;
//    }

    private String type;
    private Class typeC;

    YooPaySubModule(String type, Class typeC){
        this.type = type;
        this.typeC= typeC;
    }

    public String getType() {
        return type;
    }
    public Class isTypeC(String type) {
        return typeC;
    }

}
