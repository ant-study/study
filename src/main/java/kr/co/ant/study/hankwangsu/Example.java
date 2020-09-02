package kr.co.ant.study.hankwangsu;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import kr.co.ant.study.reflect.ReflectQuestion;
import kr.co.ant.study.reflect.ReflectQuestion.MemberVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 문제가 있는 Class 상속
 * @author hankk
 *
 */
@Slf4j
public class Example extends ReflectQuestion{
	
	@Override
	public Object getValue(Object vo, String fieldName) {
		if("id".equals(fieldName)) {
			return "A0001";
		}
		if("name".equals(fieldName)) {
			return "홍길동";
		}
		if("age".equals(fieldName)) {
			return 31;
		}
		if("brithday".equals(fieldName)) {
			return LocalDate.of(1989, 3, 12);
		}
		return null;
	}

	@Override
	public void setValue(Object vo, Object value, String fieldName) {
		MemberVO m  =(MemberVO)vo;
		if("id".equals(fieldName)) {
			m.setId((String) value);
		}
		if("name".equals(fieldName)) {
			m.setName((String) value);
		}
		if("age".equals(fieldName)) {
			m.setAge((int) value);
		}
		if("birthday".equals(fieldName)) {
			m.setBirthday((LocalDate) value);
		}
		
	}
	
	@Override
	public void copyProperties(Object orig, Object dest) throws Exception {
		MemberVO vo = (MemberVO) orig;
		Member m = (Member) dest;
		
		m.setId(vo.getId());
		m.setName(vo.getName());
		m.setAge(vo.getAge());
		m.setBirthday(vo.getBirthday());
		
	}
	
	/**
	 * Test 할 수 있는 Main Class 생성
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		Example e = new Example();
		e.startTest();
		
	}
	

}
