package kr.co.ant.study.mkhan;


import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.ant.study.reflect.spring.OrderController;
import kr.co.ant.study.reflect.spring.Request;
import lombok.extern.slf4j.Slf4j;

/**
 * Order 주문정보 VO
 * DeliveryStatus 배송상태 Enum
 * OrderController 주문 Controller
 * Request 요청정보 VO
 * 
 * @author hankk
 *
 */
@Slf4j
public class Ex03 {
	
	public static Map<String, Method> urlMethod;
	
	public static Object controller;
	
	public Ex03(Class clazz)throws Exception {
		urlMethod = new HashMap<>();
		createController(clazz);
		initUrlMethod(clazz);
	}
		
	
	/**
	 * 생성자에서 넘겨 받은 Class에 대한 객체 생성하여
	 * controller 변수에 저장
	 * @throws Exception
	 */
	private void createController(Class clazz)throws Exception {
		
		//controller = 객체생성
		
		try {
			
			Constructor cs = clazz.getConstructor();
			controller = cs.newInstance();
		     
//			log.debug(controller.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		특정 생성자를 얻는 방법입니다. 인자로 생성자의 파라미터를 전달해주면 됩니다. 
		가변 인자를 받고 있으므로 2개 이상일 경우 계속 나열해주면 됩니다. 아니면 배열로 전달해줘도 되구요.

		예시)
		Constructor constructor = testClass.getConstructor(String.class);  // 파라미터 1개
		Constructor constructor = testClass.getConstructor(new Class[]{String.class, String.class, Integer.class});// 파라미터 2개 이상
		
		(인자로 전달한 파라미터에 맞는 생성자가 없으면 NoSuchMethodException이 발생합니다.)
		*/
		
	}
	
	/**
	 * Reflection을 사용하여 RequestMapping Annotaion의 URL 정보와 해당 메소드 정보를 Map에 저장한다. Key = url(String), Value = Method 객체
	 * Class 내  Url 별로 메소드 정리
	 * 	 urlMethod.put(url, method);
	 *   Map<String, Methdo> ===> ["/order",saveOrder(메소드객체)];
	 */
	private void initUrlMethod(Class clazz)throws Exception {
		
		try {
			
			//OrderController 객체에 정의(또는 선언)되어 있는 메소드의 목록 생성.
			Method[] methods = clazz.getDeclaredMethods();
			
			for(Method method : methods) {
				
				//RequestMapping class에 부여된 method 읽음 
				Annotation anno = method.getAnnotation(RequestMapping.class);
				
				RequestMapping rqtMap = (RequestMapping) anno;
				
                String url = rqtMap.value()[0];
                
                log.debug("url[0] = " + url + " method = " + method);
                
                urlMethod.put(url, method);
                
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public void doService(Request request) throws Exception{
		//입력된 URL 별로 메소드를 호출 한다.
		
		Method method = urlMethod.get(request.getUrl());
		
		log.debug(method.getName());
		
		Class parameterType = method.getParameterTypes()[0];
		
		Object argument = toParameter(request, parameterType);
		
		method.invoke(controller, argument);
		
	}
	
	/**
	 * request에 파라미터 값을 넘겨받은 Class의 객체를 생성하여 값을 셋팅하여 리턴한다.
	 * @param request
	 * @param parameterType
	 * @return paraeterType 객체
	 */
	public Object toParameter(Request request, Class parameterType)throws Exception {
		
		//argument 로 전달할 값
		Object clazzParameterType = parameterType.newInstance();
		
		try {
			
			Class<? extends Request> clazz = request.getClass();
			
			//request에 담겨 있는 class의 field 목록을 생성.
			Field[] rqtFields = request.getClass().getDeclaredFields();
			
			for(Field rqtField : rqtFields) {
				//field type과 대상 클래스 형식 비교. isAssignableFrom 
				//실제 필요한 녀석은 >> Map<String, String> parameters << 요녀석임.
				if (rqtField.getType().isAssignableFrom(Map.class)) {
					
					//private field에 접근하기 위함 setAccessible(true)
					rqtField.setAccessible(true);
					
					//getParameters name 생성
					String rqtMetNm = "get"+StringUtils.capitalize(rqtField.getName());
					//getParameters method 읽어옴
					Method rqtMethod = clazz.getMethod(rqtMetNm);
					//getParameters method 실행
					Map<String, String> map  = (Map<String, String>) rqtMethod.invoke(request);
					
					Field[] objFields = parameterType.getDeclaredFields();
					
					for(Field objField : objFields) {
						
						//field setName 생성
						String methodNm = "set" + StringUtils.capitalize(objField.getName());
						
						//field type 가져옴.
						Class<?> objFieldType = objField.getType();
						
						@SuppressWarnings("unchecked")
						//set method 읽어옴
						Method objMethod = parameterType.getMethod(methodNm, objFieldType);
						
						System.out.println(objFieldType);
						
						if(String.class.isAssignableFrom(objFieldType)) {
							objMethod.invoke(clazzParameterType, map.get(objField.getName()));
						}else if(int.class.isAssignableFrom(objFieldType)) {
							objMethod.invoke(clazzParameterType, NumberUtils.parseNumber(map.get(objField.getName()), Integer.class));
							 
							//objFieldType.isAssignableFrom(Enum.class) 할 경우 false로 떨어짐. 
						}else if(Enum.class.isAssignableFrom(objFieldType)) { 
							objMethod.invoke(clazzParameterType, Enum.valueOf((Class<Enum>) objField.getType(), map.get(objField.getName())));
						}else {
							System.out.println("??");
						}
					 }
	             }else {
	            	 continue;
	             }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return clazzParameterType;
	}
	


	public static void main(String[] args)throws Exception {
		
		Ex03 s = new Ex03(OrderController.class);
		
		Request req = new Request();
		req.setUrl("/order");
		req.put("num", "111");
		req.put("goods", "컴퓨터");
		req.put("qty", "2");
		req.put("deleveryStatus", "READY");
		
		s.doService(req);
		
		Request commentRequest = new Request();
        commentRequest.setUrl("/goods/comment");
        commentRequest.put("num", "111");
        commentRequest.put("grade", "LOW");
        commentRequest.put("goods", "컴퓨터");
        commentRequest.put("comment", "컴퓨터가 안켜져요");
        s.doService(commentRequest);

		
	}
}
