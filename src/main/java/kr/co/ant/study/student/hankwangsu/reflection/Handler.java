package kr.co.ant.study.student.hankwangsu.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Handler {
	
	private MemberController controller = new MemberController();
	
	
	/**
	 * Reflection을 사용하지 않고 개발
	 * Controller에 메소드가 추가 될때마다 else if가 추가되면서 소스가 수정됨
	 * 중복코드 늘어남
	 * @param commandString
	 * @return
	 */
	public String oldCommand(String commandString) {
		String[] commArray = commandString.split("\\?");
		String methodName = commArray[0];
		
		String[] params = commArray[1].split("=");
		String paramName = params[0];
		String paramValue = params[1];
		
		log.info("Command : {}, ParamName : {}, ParamValue : {}", methodName, paramName, paramValue);
		
		if(methodName.equals("getMemberName")) {
			SearchVO vo  = new SearchVO();
			vo.setMemberId(paramValue);
			return controller.getMemberName(vo);
		}else if(methodName.equals("getMemberAddress")) {
			SearchVO vo  = new SearchVO();
			vo.setMemberId(paramValue);
			return controller.getMemberAddress(vo);
		}else if(methodName.equals("getMemberPhone")) {
			SearchVO vo  = new SearchVO();
			vo.setMemberId(paramValue);
			return controller.getMemberPhone(vo);
		}
		return null;
	}
	
	/**
	 * Reflection을 이용
	 * @param commandString
	 * @return
	 * @throws Exception
	 */
	public String reflectionCommand(String commandString)throws Exception {
		//명령어 문자열 Parsing "?" 기준으로 MethodName 가져옴
		String[] commArray = commandString.split("\\?");
		String methodName = commArray[0];
		
		//? 기준으로 Parameter Data 가져와서 "=" 기준으로 Param Name, Param Value 구분
		String[] params = commArray[1].split("=");
		String paramName = params[0];
		String paramValue = params[1];
		
		log.info("Command : {}, ParamName : {}, ParamValue : {}", methodName, paramName, paramValue);
		
		//MemberController Class 가져옴
		Class clazz = controller.getClass();
		
		//MemberController의 메소드 다 가져옴 {""getMemberName", "getMemberAdress", "getMemberPhone"}
		Method[] methods = clazz.getMethods();
		

		for(Method method : methods) {
			String mname = method.getName();
			
			//Method중 명령어로 넘어온 메소드명과 일치하는 Method 객체를 찿음			
			if(mname.equals(methodName)) {
				//MemberController Method의 인자값 Class를 가져옴 getMemberName(SearchVO vo) => SearchVO.class 가져옴
				Class<?> paramType = method.getParameterTypes()[0];//SearchVO.class
				
				//SearchVO에 명령어로 넘어온 Param Name의 setter 메소드를 찿음 ex) ParamName = memberId ==> setMemberId 메소드 
				String setMethodName = "set"+StringUtils.capitalize(paramName); //setMemberId;
				Method setMethod = paramType.getMethod(setMethodName, String.class);
				
				//MemberController Method 실행시 필요한 인자값 객체를 생성 ex) new SearchVO();
				Object vo = paramType.newInstance();
				//인자값에 값을 셋팅 ex) setMemberId("A001");
				setMethod.invoke(vo, paramValue);
				
				//Controller Method 실행 ex ) getMemberName(vo)
				return (String) method.invoke(controller, vo);
			}
		}
		
		return null;
	}
	

	/**
	 * arge : 메소드네임?param1=pramValue1
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//getMemberName?memberId=A001
		//getMemberAddress?memberId=A001
		Scanner sc = new Scanner(System.in);
        Handler h = new Handler();
        
        while(sc.hasNextLine()) {
        	
        	String command = sc.next();
        	
        	log.info("Input Command : {}", command);
        	
        	String result = h.reflectionCommand(command);
        	
        	log.info("Result :: {}", result);
    	}
	}
}
