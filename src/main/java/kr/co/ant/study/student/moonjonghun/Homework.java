package kr.co.ant.study.student.moonjonghun;

import java.lang.reflect.Field;
import java.time.LocalDate;

import kr.co.ant.study.reflect.ReflectQuestion;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Homework  extends ReflectQuestion
{

	/* test를 진행할 main 메소드 */
	public static void main (String[] args) {
		try {
			LocalDate date = LocalDate.of(2020, 1, 01);
			Homework homework = new Homework();
			homework.startTest();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object getValue(Object vo, String fieldName) throws Exception {
		Class mvClazz = vo.getClass();
		if(fieldName.equals("brithday")) {
			fieldName = "birthday";
		}
		Field fd = mvClazz.getDeclaredField(fieldName);
		fd.setAccessible(true);
		return fd.get(vo);
	}

	@Override
	public void setValue(Object vo, Object value, String filedName) throws Exception {
		Class mvClazz = vo.getClass();
		Field[] fd = mvClazz.getDeclaredFields();

		for(int i = 0 ; i < fd.length ; i++) {
			if(filedName.equals(fd[i].getName())) {
				fd[i].setAccessible(true);
				fd[i].set(vo, value);
			}
		}
	}

	@Override
	public void copyProperties(Object orig, Object dest) throws Exception {
		Class memberVO = orig.getClass();
		Class member   = dest.getClass();
		
		Field[] memberVOFd = memberVO.getDeclaredFields();
		Field[] memberFd = member.getDeclaredFields();
		
		if(memberVOFd.length == memberFd.length) {
			for(int i =0 ; i < memberVOFd.length; i++){
				memberVOFd[i].setAccessible(true);
				memberFd[i].setAccessible(true);
				memberFd[i].set(dest, memberVOFd[i].get(orig));
			}
		}
		
	}
	
}
