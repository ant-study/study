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
public class MoreLengthStrategy implements ValidateStrategy{

    /* (None Javadoc)
     * @see kr.co.ant.study.songyoona.oop.strategy.ValidateStrategy#validate()
     */
    @Override
    public void validateLength(String text, int length) throws Exception {
        log.info("자리수 MoreLength입니당");
        if(text.length() < length) {
            throw new Exception("자리수 "+length+"이상으로 입력해주세요");
        }

    }

}
