/**
 * Author : yooS
 * Create Date : 2020. 9. 2.
 */
package kr.co.ant.study.student.songyoona.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.yaml.snakeyaml.constructor.Constructor;

import kr.co.ant.study.reflect.ReflectQuestion;
import lombok.extern.slf4j.Slf4j;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 2.
 */
@Slf4j
public class ReflectA extends ReflectQuestion{

    @Override
    public Object getValue(Object vo, String fieldName) throws Exception{
        //1. 바뀐 vo에 값을 get
        Class neww = vo.getClass();

        //Field field = neww.getField(fieldName);
        Field field = neww.getDeclaredField(fieldName);
        field.setAccessible(true);

        log.info(field.getName());
        return field.get(vo);

    }

    @Override
    public void setValue(Object vo, Object value, String fieldName) throws Exception{

//      1. 클래스 정보 불러와서
//      Class origin = value.getClass();
//      Class origin = Class.forName((String) value);

        Class neww = vo.getClass();

//      2. 원하는 메서드 정보 or 필드 정보 찾아
//      getField는  public인 경우에만 field를 찾는다!
        Field field = neww.getDeclaredField(fieldName);
        //Field of = neww.getDeclaredField("name");

//      2-1. private에 접근할 수 없지. 접근할 수 있도록 설정 필요.
        field.setAccessible(true);

//      3. 찾은 메서드 or 필드의 값 get
        //field.get(value);

//      4. 새로운 vo에 찾은 값 set
        field.set(vo, value);

        log.info(field.getName());



    }

    @Override
    public void copyProperties(Object orig, Object dest) throws Exception {

        Class ori = orig.getClass();

        Field[] f = ori.getDeclaredFields();

        for (Field c : f)
            this.setValue(dest, this.getValue(orig,c.getName()), c.getName());

    }

    /**
    *
    * @createDate : 2020. 9. 2.
    * @param args
    * @throws Exception
    * @modifiedHistory :
    */
   public static void main(String[] args) throws Exception {
           ReflectA run = new ReflectA();
           run.startTest();
   }

}
