/**
 * Author : yooS
 * Create Date : 2020. 9. 3.
 */
package kr.co.ant.study.songyoona.annotation.ex1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.objenesis.instantiator.basic.NewInstanceInstantiator;
import org.springframework.util.StringUtils;

import kr.co.ant.study.reflect.annotation.AnnotationQuestion;
import kr.co.ant.study.reflect.annotation.LengthVO;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 3.
 */
public class AnnotationA extends AnnotationQuestion{
    /* (None Javadoc)
     * @see kr.co.ant.study.reflect.annotation.AnnotationQuestion#validate(java.lang.Object)
     */
    @Override
    public boolean validate(Object o) throws Exception {

        Class clazz = o.getClass();
//      another
//      Class clazz = LengthVO.class;
//      LengthVO a = (LengthVO)clazz.newInstance();

        //Class에 선언된 Field 목록 조회 (LengthVO => [address, phone])
        Field[] fields = clazz.getDeclaredFields();

        for(Field field : fields) {

            String fieldName = field.getName();

            // getValueMethod
            String getMethodName = "get"+StringUtils.capitalize(fieldName); //setMemberId;
            Method getMethod = clazz.getMethod(getMethodName);
            Object value = getMethod.invoke(o);
            System.out.println("getValue "+value.toString().length());

            // getAnnotations
            //Field에 선언된 Annotation 목록 조회 (address => MaxLength, phone => MinLength)
            Annotation[] annotations = field.getAnnotations();
            for(Annotation anno : annotations ) {
                Class<?> paramType = anno.annotationType();
                //Class의 메소드 조회


                //각 Annotation별로 처리
                if(anno instanceof MaxLength) {
                    //MaxLength(10) => 10자리 이상인 경우 오류
                    MaxLength myMax = (MaxLength)anno;

                    if(value.toString().length() >= myMax.value())
                        throw new Exception("MaxLength error");
                        System.out.println( myMax.value()+" MaxLength success "+((MaxLength) anno).value());

                }else if(anno instanceof MinLength) {
                    //MinLength(5) => 5자리 이하인 경우 오류
                    MinLength myMin = (MinLength)anno;

                    if(value.toString().length() <= myMin.value())
                        throw new Exception("MinLength error");
                        System.out.println(" MinLength success");
                }

            }
        }

        return false;
    }

    /**
    *
    * @createDate : 2020. 9. 3.
    * @param args
    * @modifiedHistory :
    */
   public static void main(String[] args) throws Exception {

       AnnotationA e = new AnnotationA();

       e.startTest();

   }

}
