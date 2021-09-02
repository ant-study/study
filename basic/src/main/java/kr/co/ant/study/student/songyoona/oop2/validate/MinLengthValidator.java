/**
 * Author : yooS
 * Create Date : 2020. 9. 16.
 */
package kr.co.ant.study.student.songyoona.oop2.validate;

/**
 * @description : target 보다 length가 커야 한다 (~이상)
 * @author : yooS
 * @createDate : 2020. 9. 16.
 */
public class MinLengthValidator implements YooANTValidator{


    @Override
    public boolean validate(int length, int target) {
        return length >= target;
    }

}
