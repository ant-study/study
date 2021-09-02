/**
 * Author : yooS
 * Create Date : 2020. 9. 16.
 */
package kr.co.ant.study.student.songyoona.oop2.validate;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 16.
 */
public class FixedLengthValidator implements YooANTValidator{

    /* (None Javadoc)
     * @see kr.co.ant.study.songyoona.oop2.validate.YooANTValidator#validate(int, int)
     */
    @Override
    public boolean validate(int length, int target) {
        return length == target;
    }

}
