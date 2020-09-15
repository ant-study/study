/**
 * Author : yooS
 * Create Date : 2020. 9. 14.
 */
package kr.co.ant.study.songyoona.oop.vo;

import kr.co.ant.study.songyoona.oop.PaySample;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 14.
 */
@Setter
@Getter
@ToString
public class DataGroup<T> {
    private String paymentType;
    private T[] data;



    public T[] getData() {
        return data;
    }


//    public void set(T[] t) {
//        this.t = t;
//    }
//
//    public T[] get() {
//        return t;
//    }
}
