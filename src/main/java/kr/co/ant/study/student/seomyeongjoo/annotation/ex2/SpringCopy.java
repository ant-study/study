package kr.co.ant.study.student.seomyeongjoo.annotation.ex2;

import kr.co.ant.study.reflect.spring.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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
public class SpringCopy {

	
	public static Map<String, Method> urlMethod;
	
	public static Object controller;
	
	public SpringCopy(Class clazz)throws Exception {
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
		controller = clazz.newInstance();
	}
	
	/**
	 * Reflection을 사용하여 RequestMapping Annotaion의 URL 정보와 해당 메소드 정보를 Map에 저장한다. Key = url(String), Value = Method 객체
	 * Class 내  Url 별로 메소드 정리
	 * 	 urlMethod.put(url, method);
	 *   Map<String, Methdo> ===> ["/order",saveOrder(메소드객체)];
	 */
	private void initUrlMethod(Class clazz)throws Exception {
		Method[] methods = clazz.getDeclaredMethods();
		int i = 0;
		for (Method m: methods) {
			RequestMapping rq = m.getAnnotation(RequestMapping.class);
			urlMethod.put(rq.value()[0], m);
		}

	}

	
	public void doService(Request request) throws Exception{
		//입력된 URL 별로 메소드를 호출 한다.
		
		Method method = urlMethod.get(request.getUrl());
		
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
		//request를 order로
		Map<String, String> parameters = request.getParameters();
		Field[] fs = parameterType.getDeclaredFields();
		Object obj = parameterType.newInstance();
		for (Field f:fs) {
			f.setAccessible(true);
			String value = parameters.get(f.getName());
			Object result = new Object();
			String methodName = "set"+StringUtils.capitalize(f.getName());
			Method method = parameterType.getMethod(methodName, f.getType());
			Class<?> cls = f.getType();
			String type = cls.getName();
			if("int".equals(type)){
				result = Integer.parseInt(value);
			}else if("java.lang.String".equals(type)){
				result = value;
			}else if(f.getType().isEnum()){
				Class clazz = Class.forName(type);
				Object[] consts = clazz.getEnumConstants();
				for(Object ob : consts){
					if(value.equals(ob.toString())){
						result = ob;
					}
				}

			}
			method.invoke(obj, result);
		}

		return obj;
	}
	
	
	public static void main(String[] args)throws Exception {
		
		SpringCopy s = new SpringCopy(OrderController.class);
		
		Request req1 = new Request();
		req1.setUrl("/order");
		req1.put("num", "111");
		req1.put("goods", "컴퓨터");
		req1.put("qty", "2");
		req1.put("deleveryStatus", "READY");
		
		s.doService(req1);
		
		Request req2 = new Request();
		
		req2.setUrl("/goods/comment");
		req2.put("num", "111");
		req2.put("goods", "컴퓨터");
		req2.put("grade", "MIDDLE");
		req2.put("comment", "어렵구로");
		s.doService(req2);
		
	}
}
