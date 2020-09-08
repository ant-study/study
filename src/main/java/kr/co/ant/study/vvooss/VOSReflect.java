package kr.co.ant.study.vvooss;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import kr.co.ant.study.reflect.ReflectQuestion;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class VOSReflect extends ReflectQuestion {

	@Override
	public Object getValue(Object vo, String fldNm) throws Exception {
		// TODO Auto-generated method stub

		Field f = null;
		Object retVal = null;
		log.debug("getValue[] started");
		//if(this.isMember(vo)) ;
		f = vo.getClass().getDeclaredField(fldNm);
		Method [] ms = vo.getClass().getMethods();
		for (Method m : ms) 
		{
			String nm = m.getName().toLowerCase();
			log.debug("getter nmis["+nm+"]");
			if (nm.contains(fldNm) && nm.startsWith("get")) 
			{
				log.debug("this is what i want getter ["+nm+"]");
				retVal = m.invoke(vo);
				log.debug("this is retval of gettingFields ["+retVal.toString()+"]");
				break;
			}
		}
		
		if (retVal == null) throw new Exception ("object vo has not getter method of ["+fldNm+"]");
		
		return retVal;
	}

	@Override
	public void setValue(Object vo, Object value, String fieldName) throws Exception {
		// TODO Auto-generated method stub
		Field f = null;
		//if(this.isMember(vo)) ;
		log.debug("setValue[] started fieldNm =["+fieldName+"]");
		Method [] ms = vo.getClass().getMethods();
		for (Method m : ms) 
		{
			String nm = m.getName().toLowerCase();
			
			
			if (nm.contains(fieldName) && nm.startsWith("set")) 
			{
				log.debug("cls name=["+ m.getParameterTypes()[0]+"]");
				log.debug("this is what i want setter["+nm+"]");
				Class cls = m.getParameterTypes()[0];
				Integer i = new Integer(10);
				
				if (cls.getTypeName().equals("int")) m.invoke(vo,Integer.parseInt(value.toString()));
				else 
					m.invoke(vo,  value);
				

				/*
				m.invoke(vo, cls.cast(value.getClass()
						                   .getMethod(cls.getName())
						                   .invoke(value)
						                   ));
				*/
				break;
			}
		}
		
	}

	@Override
	public void copyProperties(Object orig, Object dest) throws Exception {
		// TODO Auto-generated method stub
		//if(this.isMember(orig) && this.isMember(dest)) 
		//{
			for (Field f : orig.getClass().getDeclaredFields()) 
				
			{
				log.debug("DDDD"+f.getName());
				this.setValue(dest, this.getValue(orig, f.getName()), f.getName());
			}
		//}
	}

	public static void main(String[] args) throws Exception {
		
		VOSReflect e = new VOSReflect();
		e.startTest();
		
	}
	/**
	 * check object is member of VOSReflection or super class
	 * 
	 * @param vo
	 * @return
	 * @throws Exception 누구냐넌
	 */
	boolean isMember (Object vo) throws Exception {
		boolean isM = false;
		for (Class c : this.getClass().getClasses())
			if (c.isInstance(vo)) isM = true;
		
		if (!isM) throw new Exception ("sorry. we dont know who u r / whats ur type");
		return isM;
	}
	
}
