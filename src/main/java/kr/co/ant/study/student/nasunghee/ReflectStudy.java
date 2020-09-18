package kr.co.ant.study.student.nasunghee;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.util.StringUtils;

import kr.co.ant.study.reflect.ReflectQuestion;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReflectStudy extends ReflectQuestion {
	
	@Override
	public Object getValue(Object vo, String fieldName) throws Exception {

		Class c = vo.getClass();
		
		Field field = c.getDeclaredField(fieldName); 
		Method method = c.getMethod("get" + StringUtils.capitalize(fieldName)); //getField();
		System.out.println(method.invoke(vo));
		return method.invoke(vo);

	}

	@Override
	public void setValue(Object vo, Object value, String fieldName) throws Exception {
		// 목표 : vo객체에 fieldName에 해당하는 필드에 value 셋팅하기.
		// 1. 빈 vo클래스를 생성한다
		Class c = vo.getClass(); //MemberVO객체
		
		// 2. MemberVO에 있는 setter메소드를 가져온다.
		Field field = c.getDeclaredField(fieldName);
		String methodName = "set"+StringUtils.capitalize(fieldName);
		
		//Class의 메소드 조회 인자값으로 Field의 Type 넘겨줌
		Method method = c.getMethod(methodName, field.getType());

//		Method method = c.getMethod("set" + StringUtils.capitalize(fieldName), field.getType());

		// 3. setValue메소드를 실행한다.
		method.invoke(vo, value);
		System.out.println(vo.toString());
		
	}

	@Override
	public void copyProperties(Object orig, Object dest) throws Exception {
		// 목표 : orig 에 있는 field의 값을 dest에 셋팅해주기
		// 1. orig class를 가져온다
		Class origClas = orig.getClass(); //MemberVo
		// 2. orig class에 있는 field들을 가져온다.
		Field[] fields = origClas.getDeclaredFields(); //id name age birthday
		for (Field field : fields) {
			String fieldName = field.getName();
			// 3. orig 내의field에 있는 값을 가져온다.
			Object value = getValue(orig, fieldName);
			// 4. dest의 field에 가져온 값을 셋팅한다.
			setValue(dest, value, fieldName);
		}
		

	}
	

	public static void main(String[] args) throws Exception {
		
		ReflectStudy r = new ReflectStudy();
		
		r.startTest();
		
	}
}
