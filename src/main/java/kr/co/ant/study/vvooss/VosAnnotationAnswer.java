package kr.co.ant.study.vvooss;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import kr.co.ant.study.reflect.annotation.AnnotationQuestion;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VosAnnotationAnswer extends AnnotationQuestion {

	@Override
	public boolean validate(Object o) throws Exception {
		// TODO Auto-generated method stub
		
		for(Field f : o.getClass().getDeclaredFields()) {
			
			f.setAccessible(true);
			log.debug("f.name=["+f.getName()+"],value=["+f.get(o).toString()+"]");
			for(Annotation a : f.getDeclaredAnnotations()) {
				log.debug("a.name=["+a.getClass().getName()+"],value=["+f.get(o).toString()+"]");
				if(a.annotationType()==MaxLength.class && f.getType() == String.class) {
					log.debug("maximum size["+((MaxLength) a).value()+"] and object's size["+f.get(o).toString().length()+"]");
					if (f.get(o).toString().length() > ((MaxLength) a).value()) {
						// throw new Exception (((MaxLength) a).msg());
						throw new Exception ("sorry , bigger than maximum size["+((MaxLength) a).value()+"]");
					}
				}
				if(a.annotationType()==MinLength.class && f.getType() == String.class) {
					log.debug(" size["+((MinLength) a).value()+"]");
					if (f.get(o).toString().length() < ((MinLength) a).value()) {
						//throw new Exception (((MinLength) a).msg());
						throw new Exception ("sorry , smaller than minimum size");
					}
				}
			}
		}
		
		return true;
	}

	public static void main(String[] args) throws Exception {
		
		VosAnnotationAnswer e = new VosAnnotationAnswer();
		e.startTest();
		
	}
	
}
