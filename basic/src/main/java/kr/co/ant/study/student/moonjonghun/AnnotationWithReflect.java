package kr.co.ant.study.student.moonjonghun;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.util.StringUtils;

import kr.co.ant.study.reflect.annotation.AnnotationQuestion;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnnotationWithReflect extends AnnotationQuestion{

	public static void main (String[] args) {
		try {
			AnnotationWithReflect e = new AnnotationWithReflect();
			e.startTest();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean validate(Object o) throws Exception {
		Class clazz = o.getClass();
		Field[] fds = clazz.getDeclaredFields();
		
		//fds ==> lengthVO
		for(Field fd : fds) {
			//fd ==> phoneNum , address
			//field값 가져오기 
			Method getMethod = clazz.getMethod("get"+ StringUtils.capitalize(fd.getName()));
			Object obj = getMethod.invoke(o);
			String strItem = "";
			
			if(obj instanceof Integer) {
				strItem = Integer.toString((int) obj);
			}else if(obj instanceof String) {
				strItem = (String) obj;
			}
			
			//인스턴스 가져오기 
			//어노테이션 클래스 가져오기		
			Annotation[] annos = fd.getAnnotations();
			String msg = "";
			boolean valid = false;
			for(Annotation anno: annos) {
				msg = "";
				if(anno instanceof MaxLength) {
					int max = ((MaxLength) anno).value();
					if(strItem.length() > max) { //10
						msg += "주소값의 최대입력값을 초과하셨습니다.";
						valid = true;
					}
				}else if(anno instanceof MinLength) {
					int min = ((MinLength) anno).value();
					if( min > strItem.length() ) { //6 > 10
						msg += "전화번호값의 최소입력값보다 작습니다.";
						valid = true;
					}
				}
				
				if(valid)//flag가 true면 throws Exception
				{
					//throw new Exception(msg);
					log.debug(msg);
				}
			}
			
		}
		
		return false;
	}
	
}
