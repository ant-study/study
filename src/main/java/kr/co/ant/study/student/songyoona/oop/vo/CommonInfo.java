/**
 * Author : yooS
 * Create Date : 2020. 9. 13.
 */
package kr.co.ant.study.student.songyoona.oop.vo;

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

    // enum 으로 객체 받아오는건 tobe....
    private YooPaySubModule PaySubModule;
//  CommonInfo c = new CommonInfo();
//  YooPaySubModule m = YooPaySubModule.valueOf(type);
//  c.setT(m.isTypeC(type).newInstance());
  //this.typeO = (T)m.isTypeC(type).newInstance();


    // 제네릭으로 뭐가오든 받을수있게..
    private T typeObj;

    /**
     * paymentType 에 맞는 class Object return
     * @createDate : 2020. 9. 13.
     * @param type
     * @modifiedHistory :
     */
    public T setObject(CommonInfo t) throws Exception {
        this.typeObj = (T) t.getTypeObj();
        return typeObj;
    }






}
