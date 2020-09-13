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
    private double amount;
    private String paymentType;
    private String tranDate;

    //private PaySubModule paySubModule;  // 제네릭으로 뭐가오든 받을수있게..
    private YooPaySubModule PaySubModule;

    private T t;
    /**
     *
     * @createDate : 2020. 9. 13.
     * @param type
     * @modifiedHistory :
     */
    public T setPaySubModule(String type) {
        //Class c = YooPaySubModule.valueOf(type);
       // this.t = YooPaySubModule.valueOf(type).getClass().newInstance();
        return t;
    }






}
