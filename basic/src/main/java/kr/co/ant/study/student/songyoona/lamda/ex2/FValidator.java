/**
 * Author : yooS
 * Create Date : 2020. 9. 18.
 */
package kr.co.ant.study.student.songyoona.lamda.ex2;

import java.util.function.BiPredicate;

import kr.co.ant.study.student.songyoona.oop2.service.ValidateException;
import kr.co.ant.study.student.songyoona.oop2.validate.YooANTValidator;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 18.
 */

/*
 * 이번 문제의 요지는 Functional 클래스를 이렇게 사용할 수 있다와
        기존 심플한 Validate를 사용 하다가 전혀 다른 Validate를 바꿔줘도 Payment 구현체는 전혀 손안되도 된다
        대부분 Validator Interface를 만들어 놨죠?
    Funtional 클래스를 사용하는 Validator를 구현한다음 기존 Validator를 교체 합시다.
    FixedValidate, MinLengthValidate => functional 클래스를 사용한 Validate 하나로 처리 가능
    ** hint : BIPredicate Function 사용

    예제)
    AnswerCardPayment cardPayment = new AnswerCardPayment(infoVO, new FixedLengthValidator());
     ==> AnswerCardPayment cardPayment = new AnswerCardPayment(infoVO, 요기다가 구현한 클래스 교체);
 * */
public class FValidator implements YooANTValidator {

//    public BiPredicate<Integer, Integer> fixed = (l, t) -> l == t;
//
//    public BiPredicate<Integer, Integer> min = (ll, tt) -> ll >= tt;


    private BiPredicate<Integer, Integer> f;

    /**
     * @param f functional validate를 받는 생성자
     */
    public FValidator(BiPredicate<Integer, Integer> f) {
        super();
        this.f = f;
    }

    /* (None Javadoc)
     * @see kr.co.ant.study.student.songyoona.oop2.validate.YooANTValidator#validate(int, int)
     */
    @Override
    public boolean validate(int length, int target) throws ValidateException {

        boolean result = f.test(length, target);
        return result;
    }

}
