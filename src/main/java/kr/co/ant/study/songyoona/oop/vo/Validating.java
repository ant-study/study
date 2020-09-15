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
 * @description : 유효성검사 class
 * @author : yooS
 * @createDate : 2020. 9. 14.
 */
@Getter
@Setter
@ToString
public class Validating {

    private ValidateStrategy validateStrategy;  // 유효성검사 전략패턴


    public void validate(String text, int length) throws Exception {
        // 자리수 길이 체크
        validateStrategy.validateLength(text, length);
    }

    public void setValidate(ValidateStrategy validate) {
        this.validateStrategy = validate;
    }




}
