/**
 * Author : yooS
 * Create Date : 2020. 9. 14.
 */
package kr.co.ant.study.songyoona.oop.vo;

import kr.co.ant.study.songyoona.oop.strategy.ValidateStrategy;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 14.
 */
@Getter
@Setter
@ToString
public class Validating {

    private ValidateStrategy validateStrategy;

    public void validate(String text, int length) throws Exception {
        validateStrategy.validate(text, length);
    }

    public void setValidate(ValidateStrategy validate) {
        this.validateStrategy = validate;
    }

//    // 정해진 자릿수
//    public void fixLengthValidate(ValidateStrategy validateStrategy) {
//        this.validateStrategy = validateStrategy;
//    }
//
//    // 자릿수 이상
//    public void moreLengthValidate(ValidateStrategy validateStrategy) {
//        this.validateStrategy = validateStrategy;
//    }


}
