/**
 * Author : yooS
 * Create Date : 2020. 9. 3.
 */
package kr.co.ant.study.student.songyoona.annotation.ex1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description : maxLength
 * @author : yooS
 * @createDate : 2020. 9. 3.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxLength {

    int value();


}
