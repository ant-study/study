package kr.co.ant.study.moonjonghun;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.ant.study.reflect.spring.DeliveryStatus;
import kr.co.ant.study.reflect.spring.Order;
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
		Object obj =  clazz.newInstance();
		controller = obj;
	}
	
	/**
	 * Reflection을 사용하여 RequestMapping Annotaion의 URL 정보와 해당 메소드 정보를 Map에 저장한다. Key = url(String), Value = Method 객체
	 * Class 내  Url 별로 메소드 정리
	 * 	 urlMethod.put(url, method);
	 *   Map<String, Methdo> ===> ["/order",saveOrder(메소드객체)];
	 */
	private void initUrlMethod(Class clazz)throws Exception {
		//클래스에서 메소드를 모두 가져오고 List
		Method[] methods = clazz.getDeclaredMethods();
		
		//메소드에 붙어있는 어노테이션 정보 가져온다. List
		for(Method method : methods) {
			Annotation anno = method.getAnnotation(RequestMapping.class);
			String[] paths =((RequestMapping) anno).value();	

			log.info("path Value : {}", paths);
			
			//request요청 경로가 여러개일수 있지만 첫번째 경로만 가져오도록 한다.
			urlMethod.put(paths[0], method);
		}
		
		//request path와 method명을 매치시켰다!
		
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
		Map<String, String> map = request.getParameters();
		
		log.info("class name : {}",parameterType.getName());
		
		//매개변수가 Order 라면
		if(parameterType.equals(Order.class)) {
			Order order = (Order) parameterType.newInstance();
			
			order.setNum(Integer.parseInt(map.get("num")));
			order.setGoods(map.get("goods"));
			order.setQty(Integer.parseInt(map.get("qty")));
			order.setDeleveryStatus(DeliveryStatus.valueOf(map.get("deleveryStatus")));
			return order;
		}
		
		//매개변수가 int 라면
		if(parameterType.equals(int.class)) {
			int num = Integer.parseInt(map.get("num"));
			return num;
		}
		
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
		deleveryStatusRequest.setUrl("/order/deliveryStatus");
		deleveryStatusRequest.put("num", "111");
		s.doService(deleveryStatusRequest);
		
		s.doService(deleveryStatusRequest);
		
	}
}
