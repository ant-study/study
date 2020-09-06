package kr.co.ant.study.choijongmin;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.util.StringUtils;

import kr.co.ant.study.reflect.annotation.AnnotationQuestion;
import lombok.Value;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReflectAnnotationTest extends AnnotationQuestion {

	public Object getValue(Object vo, String fieldName) throws Exception {
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
			String value = (String) getValue(o, field.getName());
			String message = field.getName() + "는 ";
			
			//1번?
			for(Annotation anno : annotations ) {
				int annoVal = 0;
				if ( anno instanceof MaxLength ) {
					MaxLength max = (MaxLength) anno;
					annoVal = max.value();
					if ( value.length() > annoVal ) {
						message = message + annoVal + "자 보다 클 수 없습니다.";
						log.debug(message);
					}
				}
				
				if ( anno instanceof MinLength ) {
					MinLength min = (MinLength) anno;
					annoVal = min.value();
					
					if ( value.length() < annoVal ) {
						message = message + annoVal + "자 보다 작을 수 없습니다.";
						log.debug(message);
					}
				}
			}
			
			//2번?
			if ( field.isAnnotationPresent(MaxLength.class) ) {
				MaxLength iValue = field.getAnnotation(MaxLength.class);
				if ( value.length() > iValue.value() ) {
					message = message + iValue.value() + "자 보다 클 수 없습니다.";
					log.debug(message);
				}
			} else if ( field.isAnnotationPresent(MinLength.class) ) {
				MinLength iValue = field.getAnnotation(MinLength.class);
				if ( value.length() < iValue.value() ) {
					message = message + iValue.value() + "자 보다 작을 수 없습니다.";
					log.debug(message);
				}
			} else {
				//다른거?
			}
		}
		
		// TODO Auto-generated method stub
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		ReflectAnnotationTest e = new ReflectAnnotationTest();
		e.startTest();
	}
	
}