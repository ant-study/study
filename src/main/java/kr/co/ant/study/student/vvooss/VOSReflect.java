package kr.co.ant.study.student.vvooss;

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
		log.debug("setValue[] started fieldNm =["+fieldName+"]valueType=["+value.getClass().getTypeName()+"]");
		Method [] ms = vo.getClass().getMethods();
		for (Method m : ms) 
		{
			String nm = m.getName().toLowerCase();
			
			
			if (nm.contains(fieldName.toLowerCase()) && nm.startsWith("set")) 
			{
				log.debug("cls name=["+ m.getParameterTypes()[0]+"]");
				log.debug("this is what i want setter["+nm+"]");
				Class cls = m.getParameterTypes()[0];
				Integer i = new Integer(10);
				
				// invoke method의 파라미터 타입과 value의 타입이 일치하는지?
				if (cls.getTypeName().equals(value.getClass().getTypeName())) m.invoke(vo,  value);
				else
				{
					// 원시값인지? int
					if (cls.getTypeName().equals("int")) m.invoke(vo,Integer.parseInt(value.toString()));
					// 원시값인지? long
					else if (cls.getTypeName().equals("long")) m.invoke(vo,Long.parseLong(value.toString()));
					// 원시값인지? ...
					
					// 원시값이 아녀?
					else 
					{
						// 너의 타입에 메소드가 갯수가 0보다 큰지
						if (cls.getMethods().length > 0) 
						{
							for (Method cm : cls.getMethods())
							{
								log.debug("원시타입이 아닌 객체의 게터를 찾자.["+cm.getName()+"] 줄수있는게 뭐여?["+cm.getReturnType().getTypeName()+"]");
								// 너의 메서드의 리턴타입이 셋터의 파라미터타입과 일치하는지?
								if (cm.getReturnType().getTypeName().equals(cls.getTypeName()))
								{
									log.debug("찾은거 가터.["+cm.getReturnType().getTypeName()+"]");
									
									// get은 보통 파라미터갯수가 없엉.
									if (cm.getParameterCount() > 0) 
									{
										if(cm.getParameterCount()==1) 
										{
											if (cm.getParameters()[0].getType().getName().equals(value.getClass().getTypeName()))
											{
												m.invoke(vo,cm.invoke(cls,value));
												
											}
										}
										else 
										{
											// object array는 난중에. 
											log.debug("미안혀,, 시간없어. 넌 원하는게 많구먼.["+cm.getParameterCount()+"]");
										}
									}
									else 
									{
										m.invoke(vo, cm.invoke(value));
									}
									;
								}
							}
						}
					}

				
				}
				/*
				m.invoke(vo, cls.cast(value.getClass()
						                   .getMethod(cls.getName())
						                   .invoke(value)
						                   ));
				*/
				break;
			}
			else 
			{
				log.debug("who are u ["+nm+"]");
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
