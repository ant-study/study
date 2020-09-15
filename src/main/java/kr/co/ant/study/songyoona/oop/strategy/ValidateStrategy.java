/**
 * Author : yooS
 * Create Date : 2020. 9. 14.
 */
package kr.co.ant.study.songyoona.oop.strategy;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 14.
 */
public interface ValidateStrategy {

    public void validateLength(String text, int length) throws Exception;

}
