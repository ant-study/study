package kr.co.ant.study.mkhan;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.util.StringUtils;

import kr.co.ant.study.reflect.ReflectQuestion;

public class Ex01 extends ReflectQuestion  {

	@Override
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
	public void setValue(Object vo, Object value, String filedName) throws Exception {
		
		Class<? extends Object> clazz = vo.getClass(); 
		
		Field field = clazz.getDeclaredField(filedName);
		
		//capitalize는 첫글자만 대문자로 변환 Apache Commons Lang에도 있음, 아니면 substring 0,1 해서 toUpperCase를 사용해서 변환
		String methodName = "set"+StringUtils.capitalize(filedName);
		
		//Class의 메소드 조회 인자값으로 Field의 Type 넘겨줌
		Method method = clazz.getMethod(methodName, field.getType());
		
		//set 메소드를 실행 하면서 인자값으로 셋팅할 값 넘겨줌
		method.invoke(vo, value);
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void copyProperties(Object orig, Object dest) throws Exception {
		
		Class origClass = orig.getClass();
		
		//원본 VO의 Field 목록 조회
		Field[] fields = origClass.getDeclaredFields();
		
		for(Field field : fields) {
			String fieldName = field.getName();
			Object value = getValue(orig, fieldName);
			setValue(dest, value, fieldName);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		Ex01 ex01 = new Ex01();
		ex01.startTest();
		
	}

}
