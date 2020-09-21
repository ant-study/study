/**
 * Author : yooS
 * Create Date : 2020. 9. 20.
 */
package kr.co.ant.study.student.songyoona.oop2.validate;

import java.util.function.BiPredicate;

import kr.co.ant.study.student.songyoona.oop2.service.ValidateException;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 20.
 */
public class FixedLengthLamdaValidator implements YooANTValidator{

    /* (None Javadoc)
     * @see kr.co.ant.study.student.songyoona.oop2.validate.YooANTValidator#validate(int, int)
     */
    @Override
    public boolean validate(int length, int target) throws ValidateException {

        BiPredicate<Integer, Integer> v = (l, t) -> l == t;
        return v.test(length, target);
    }
}
