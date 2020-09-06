package kr.co.ant.study.seomyeongjoo.annotation;

import kr.co.ant.study.reflect.annotation.AnnotationQuestion;
import kr.co.ant.study.reflect.annotation.LengthVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Optional;
@Slf4j
public class AnnotationExample extends AnnotationQuestion {

    private Object getValue(Object vo, String fieldName) throws Exception {
        //Object의 Class 객체 조회
        Class clazz = vo.getClass();

        //FieldName으로 Class의 선언된 Field 조회,  getField(fieldName)은 public field만 가져옴
        Field field = clazz.getDeclaredField(fieldName);

        //capitalize는 첫글자만 대문자로 변환 Apache Commons Lang에도 있음, 아니면 substring 0,1 해서 toUpperCase를 사용해서 변환
        String methodName = "get"+StringUtils.capitalize(fieldName); //name => getName

        //Class의 메소드 조회
        Method method = clazz.getMethod(methodName);

        return method.invoke(vo);

    }

    @Override
    public boolean validate(Object o) throws Exception {
        Class clazz = o.getClass();

        //Class에 선언된 Field 목록 조회 (LengthVO => [address, phone])
        Field[] fields = clazz.getDeclaredFields();

        for(Field field : fields) {
            //Field에 선언된 Annotation 목록 조회 (address => MaxLength, phone => MinLength)
            Annotation[] annotations = field.getAnnotations();
            for(Annotation anno : annotations ) {
                //각 Annotation별로 처리
                Object obj = getValue(o, field.getName());
                int inputLength = obj.toString().length();

                if(anno instanceof MaxLength) {
                    MaxLength max = (MaxLength) anno;
                    int maxvalue = max.value();
                    if(maxvalue < inputLength) {
                        log.debug("최대 {} 자리 수만 입력할 수 있습니다.", maxvalue);
                        throw new Exception("땡");
                    }
                }

                if(anno instanceof MinLength) {
                    MinLength min = (MinLength) anno;
                    int minvalue = min.value();
                    if(minvalue > inputLength) {
                        log.debug("최소 {}자리 수를 입력해야 합니다.", minvalue);
                        throw new Exception("땡");
                    }
                }

            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        AnnotationExample e = new AnnotationExample();
        e.startTest();
    }
}
