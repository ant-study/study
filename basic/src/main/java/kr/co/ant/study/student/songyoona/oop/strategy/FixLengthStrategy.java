/**
 * Author : yooS
 * Create Date : 2020. 9. 14.
 */
package kr.co.ant.study.student.songyoona.oop.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 14.
 */
@Slf4j
public class FixLengthStrategy implements ValidateStrategy{

    /* (None Javadoc)
     * @see kr.co.ant.study.songyoona.oop.strategy.ValidateStrategy#validate()
     */
    @Override
    public void validateLength(String text, int length) throws Exception {
        log.info("고정된 FixLength 자리수입니다");
        if(text.length() != length) {
            throw new Exception(length+"자리로 자리수 맞춰주세요~~~");
        }
    }

}
