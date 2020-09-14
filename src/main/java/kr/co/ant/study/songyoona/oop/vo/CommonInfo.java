/**
 * Author : yooS
 * Create Date : 2020. 9. 13.
 */
package kr.co.ant.study.songyoona.oop.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 13.
 */
@Getter
@Setter
@ToString
public class CommonInfo<T>{
    private String productId;
    private String productName;
    private String paymentType;
    private double amount;
    private String tranDate;

    private YooPaySubModule PaySubModule;
    // 제네릭으로 뭐가오든 받을수있게..
    private T typeO;

    /**
     * paymentType 에 맞는 class Object return
     * @createDate : 2020. 9. 13.
     * @param type
     * @modifiedHistory :
     */
    public T setPaySubModule(T t) throws Exception {
//        CommonInfo c = new CommonInfo();
//        YooPaySubModule m = YooPaySubModule.valueOf(type);
//        c.setT(m.isTypeC(type).newInstance());
        //this.typeO = (T)m.isTypeC(type).newInstance();
        this.typeO = t;
        return typeO;
    }

    public void setTypeO(T t) {
        this.typeO = t;
    }

    public T getTypeO() {
        return typeO;
    }





}
