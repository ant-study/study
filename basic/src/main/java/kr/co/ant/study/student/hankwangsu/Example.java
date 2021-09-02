package kr.co.ant.study.student.hankwangsu;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import kr.co.ant.study.reflect.ReflectQuestion;
import kr.co.ant.study.reflect.annotation.LengthVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 문제가 있는 Class 상속
 * @author hankk
 *
 */
@Slf4j
public class Example extends ReflectQuestion{
	
	@Override
	public Object getValue(Object vo, String fieldName) throws Exception {
		//Object의 Class 객체 조회
		Class clazz = vo.getClass();
		
		//FieldName으로 Class의 선언된 Field 조회,  getField(fieldName)은 public field만 가져옴
		Field field = clazz.getDeclaredField(fieldName);
		
		//capitalize는 첫글자만 대문자로 변환 Apache Commons Lang에도 있음, 아니면 substring 0,1 해서 toUpperCase를 사용해서 변환
		String methodName = "get"+StringUtils.capitalize(fieldName); //name => getName
		
		//Class의 메소드 조회
		Method method = clazz.getMethod(methodName);
		
		
		
		MemberVO s = new MemberVO();
		s.setName("김");
		String name = (String) method.invoke(s); //MemberVO에 있는 getName 메소드
		System.out.println("s의 네임 :: "+name);
		
		MemberVO a = new MemberVO();
		a.setName("홍");
		name = (String) method.invoke(s); //MemberVO에 있는 getName 메소드
		System.out.println("a의 네임 :: "+name);
		
		return method.invoke(vo);
		
	}

	@Override
	public void setValue(Object vo, Object value, String fieldName) throws Exception {
		//Object의 Class 객체 조회
		Class clazz = vo.getClass();
		
		//FieldName으로 Class의 선언된 Field 조회,  getField(fieldName)은 public field만 가져옴
		Field field = clazz.getDeclaredField(fieldName);
				
		//capitalize는 첫글자만 대문자로 변환 Apache Commons Lang에도 있음, 아니면 substring 0,1 해서 toUpperCase를 사용해서 변환
		String methodName = "set"+StringUtils.capitalize(fieldName);
		
		//Class의 메소드 조회 인자값으로 Field의 Type 넘겨줌
		Method method = clazz.getMethod(methodName, field.getType());
		
		//set 메소드를 실행 하면서 인자값으로 셋팅할 값 넘겨줌
		method.invoke(vo, value);
		
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
	
	/**
	 * Test 할 수 있는 Main Class 생성
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		LengthVO a = new LengthVO();
		LengthVO b = new LengthVO();
		System.out.println(a);
		System.out.println(b);
		Example e = new Example();
		MemberVO c = new MemberVO();
		MemberVO d = new MemberVO();
		System.out.println(c.equals(d));
		System.out.println(d);
		
	}
	

}
