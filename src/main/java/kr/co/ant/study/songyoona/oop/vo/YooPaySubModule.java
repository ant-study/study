/**
 * Author : yooS
 * Create Date : 2020. 9. 13.
 */
package kr.co.ant.study.songyoona.oop.vo;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 13.
 */
public enum YooPaySubModule {
    CARD(CardInfo.class),
    BANK(BankInfo.class),
    Mobile(MobileInfo.class);

    private Class value;

    /**
     * @param value
     */
    private YooPaySubModule(Class value) {
        this.value = value;
    }




}
