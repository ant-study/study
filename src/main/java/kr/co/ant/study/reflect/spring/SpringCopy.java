package kr.co.ant.study.reflect.spring;

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
	}
	
	/**
	 * Reflection을 사용하여 RequestMapping Annotaion의 URL 정보와 해당 메소드 정보를 Map에 저장한다. Key = url(String), Value = Method 객체
	 * Class 내  Url 별로 메소드 정리
	 * 	 urlMethod.put(url, method);
	 *   Map<String, Methdo> ===> ["/order",saveOrder(메소드객체)];
	 */
	private void initUrlMethod(Class clazz)throws Exception {
		
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
		return null;
	}
	
	
	public static void main(String[] args)throws Exception {
		
		SpringCopy s = new SpringCopy(OrderController.class);
		
		Request req = new Request();
		req.setUrl("/order");
		req.put("num", "111");
		req.put("goods", "컴퓨터");
		req.put("qty", "2");
		req.put("deleveryStatus", "READY");
		
		s.doService(req);
		
		Request deleveryStatusRequest = new Request();
		deleveryStatusRequest.setUrl("/order/deleveryStatus");
		deleveryStatusRequest.put("num", "111");
		s.doService(deleveryStatusRequest);
		
		s.doService(deleveryStatusRequest);
		
	}
}
