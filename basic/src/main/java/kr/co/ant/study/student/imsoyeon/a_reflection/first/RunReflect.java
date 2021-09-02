package kr.co.ant.study.student.imsoyeon.a_reflection.first;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

import kr.co.ant.study.reflect.ReflectQuestion;
import lombok.extern.slf4j.Slf4j;

/* 		
		vo 생성 & 메서드 호출 등은 다 startTest()안에 있음.
		메서드 구현만 하면 됨
 */
@Slf4j
public class RunReflect extends ReflectQuestion {

	/*
	 * java.lang.reflect 
	 * 	: 클래스의 정보를 불러온 다음에, 그 정보에서 변수 fields, 생성자 constructor, 메소드 method 등의 정보 가져와서 보여주는 애
	 * */
	@Override
	public Object getValue(Object vo, String fieldName) throws Exception {
//		1. 바뀐 vo에 값을 get
		Class neww = vo.getClass();		
		Field field = neww.getDeclaredField(fieldName);
		field.setAccessible(true);
		
		return field.get(vo);
	}

	@Override
	public void setValue(Object vo, Object value, String filedName) throws Exception {
//		1. 클래스 정보 불러와서
//		Class origin = value.getClass();
//		Class origin = Class.forName("path?"); 
		
		Class neww = vo.getClass();
		
//		2. 원하는 메서드 정보 or 필드 정보 찾아
//		getField는 public인 경우에만 field를 찾는다!
//		Field field1 = origin.getField(filedName);
		Field field2 = neww.getDeclaredField(filedName);
		
//		2-1. private에 접근할 수 없지. 접근할 수 있도록 설정 필요.
		field2.setAccessible(true);
		
//		3. 찾은 메서드 or 필드의 값 get
//		field1.get(value);
		
//		4. 새로운 vo에 찾은 값 set
		field2.set(vo, value);
		log.info(field2.getName());
		
	}

	@Override
	public void copyProperties(Object orig, Object dest) throws Exception {
		Class origin = orig.getClass();
		Class member = dest.getClass();
		
		Field[] field_orig = origin.getDeclaredFields();	//원래꺼
		Field[] field_mem = member.getDeclaredFields();	//새거
		
		for (Field origF : field_orig) {
			for (Field memF : field_mem) {
//				접근 가능 설정 1.
//				origF.setAccessible(true);
//				memF.setAccessible(true);
				
//				접근 가능 설정 2.
				AccessibleObject.setAccessible(new AccessibleObject[] {origF, memF}, true);
				
				if (origF.getName() == memF.getName()) {
					memF.set(dest, origF.get(orig));	/**/
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {

//		sampleData : 홍길동 / 31 / 19890312 / A0001
		RunReflect run = new RunReflect();
		run.startTest();
	}

}
