package kr.co.ant.study.mkhan;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.util.StringUtils;

import kr.co.ant.study.mkhan.annotation.AnnotationQuestion;
import kr.co.ant.study.mkhan.annotation.LengthValidate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ex02 extends AnnotationQuestion {
	
	public Object getValue(Object vo, String fieldName) throws Exception {
		
		Class<? extends Object> clazz = vo.getClass(); 
		
		Field field = clazz.getDeclaredField(fieldName);
		
		//capitalize는 첫글자만 대문자로 변환 Apache Commons Lang에도 있음, 아니면 substring 0,1 해서 toUpperCase를 사용해서 변환
		String methodName = "get"+StringUtils.capitalize(fieldName);
		
		//Class의 메소드 조회
		Method method = clazz.getMethod(methodName);
		
		//get Property는 인자가 없기때문에 Object만 넘겨서 Method 실행후 결과값 리턴
		return method.invoke(vo);
		
	}

	@Override
	public boolean validate(Object vo) throws Exception {
		
		Class<? extends Object> clazz = vo.getClass();
		Field[] fields = clazz.getDeclaredFields();
		
		boolean isValidate = true;
		
		try {
			
			for(Field field : fields ) {
				
				String fieldName = field.getName();
				
				Object value = getValue(vo, fieldName);
				String str = (String) value;
				
				Annotation[] annotations = field.getAnnotations();
				for(Annotation anno : annotations ) {
					
					if(anno instanceof LengthValidate) {
						
						LengthValidate length = (LengthValidate) anno;
						
						if(str.length() > length.maxValue() && "01".equals(length.code())) {
							isValidate = false;
							throw new Exception(fieldName + " 는 10자리를 초과할 수 없습니다.");
						}
						
						if(str.length() < length.minValue() && "02".equals(length.code())) {
							isValidate = false;
							throw new Exception(fieldName + " 는 최소 10자이상 입력이 필요합니다.");
						}
						
					}
					
//					if (anno instanceof MaxLength) {
//						MaxLength maxLength = (MaxLength) anno;
//						if(str.length() > maxLength.maxValue()) {
//							isValidate = false;
//							throw new Exception(field.getName() + " 는 10자리를 초과할 수 없습니다.");
//						}
//					}else if (anno instanceof MinLength) {
//						MinLength minLength = (MinLength) anno;
//						if(str.length() < minLength.minValue()) {
//							isValidate = false;
//							throw new Exception(field.getName() + " 는 최소 10자이상 입력이 필요합니다.");
//						}
//					}
				}
			}
			
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		
		return isValidate;
	}
	
	public static void main(String[] args) throws Exception {
		Ex02 ex02 = new Ex02();
		ex02.startTest();
	}

}
