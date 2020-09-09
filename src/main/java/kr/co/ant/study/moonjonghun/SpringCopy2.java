package kr.co.ant.study.moonjonghun;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.ant.study.reflect.spring.Comment;
import kr.co.ant.study.reflect.spring.DeliveryStatus;
import kr.co.ant.study.reflect.spring.GoodsEvaluationGrade;
import kr.co.ant.study.reflect.spring.Order;
import kr.co.ant.study.reflect.spring.OrderController;
import lombok.extern.slf4j.Slf4j;

/**
 * Order 주문정보 VO
 * DeliveryStatus 배송상태 Enum
 * OrderController 주문 Controller
 * Request 요청정보 VO
 * 
 * @author 문종훈
 *
 */
@Slf4j
public class SpringCopy2 {

	
	public static Map<String, Method> urlMethod;
	
	public static Object controller;
	
	public SpringCopy2(Class clazz)throws Exception {
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
		//newInstance로 객체를 생성할때 클래스의 기본생성자를 호출한다.
		//클래스에 기본생성자가 포함되어있지 않더라도 컴파일시 자동적으로 기본생성자를 생성해준다.
		controller = clazz.newInstance();
	}
	
	/**
	 * Reflection을 사용하여 RequestMapping Annotaion의 URL 정보와 해당 메소드 정보를 Map에 저장한다. Key = url(String), Value = Method 객체
	 * Class 내  Url 별로 메소드 정리
	 * 	 urlMethod.put(url, method);
	 *   Map<String, Methdo> ===> ["/order",saveOrder(메소드객체)];
	 */
	private void initUrlMethod(Class clazz)throws Exception {
		//클래스에서 메소드를 모두 가져오고 List
//		Method[] methods = ;
		
		//메소드에 붙어있는 어노테이션 정보 가져온다. List
		for(Method method : clazz.getDeclaredMethods()) {
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
		
		for(Method reqMethod : request.getClass().getDeclaredMethods()) {
			if(reqMethod.getName() == "getParameters") {
				Map<?, ? > map =(Map<?, ?>) reqMethod.invoke(request);
				
				log.info("class name : {}",parameterType.getName());
				
				//매개변수가 Order 혹은  Comment 라면
				if(parameterType.equals(Order.class) || parameterType.equals(Comment.class)) {
					
					Object obj = null;
					
					if(parameterType.equals(Order.class)) {
						obj = (Order) parameterType.newInstance();
					} else {
						obj = (Comment) parameterType.newInstance();
					}
					
					//메소드별 매개변수의 Field[] 를 가져온다.
					Field[] orderFields = parameterType.getDeclaredFields();
					
					for(Field fd : orderFields) {
						Method getMethod = parameterType.getDeclaredMethod("set"+StringUtils.capitalize(fd.getName()), fd.getType());
						if(fd.getType().equals(int.class)) {
							int paramVal = (int) map.get(fd.getName());	
							getMethod.invoke(obj, paramVal);
						} else if(fd.getType().isEnum()){
							if(parameterType.equals(Order.class)) {
								DeliveryStatus paramVal = DeliveryStatus.valueOf((String) map.get(fd.getName()));
								getMethod.invoke(obj, paramVal);
							} else {
								GoodsEvaluationGrade paramVal = GoodsEvaluationGrade.valueOf((String) map.get(fd.getName()));
								getMethod.invoke(obj, paramVal);
							}
						} else {
							String paramVal =(String) map.get(fd.getName());
							getMethod.invoke(obj, paramVal);
						}
					}
					
					return obj;
				}
				
				//매개변수가 int 라면
				if(parameterType.equals(int.class)) {
					int num = (int) map.get("num");
					return num;
				}
			}
		}
		
		return null;
	}
	
	
	public static void main(String[] args) {
		try {
			SpringCopy2 s = new SpringCopy2(OrderController.class);
			
			Request req = new Request();
			req.setUrl("/order");
			req.put("num", 111);
			req.put("goods", "컴퓨터");
			req.put("qty", 2);
			req.put("deleveryStatus", "READY");
			
			s.doService(req);
			
			Request deleveryStatusRequest = new Request();
			deleveryStatusRequest.setUrl("/order/deliveryStatus");
			deleveryStatusRequest.put("num", 111);
			s.doService(deleveryStatusRequest);
			
			Request commentRequest = new Request();
			commentRequest.setUrl("/goods/comment");
			commentRequest.put("num", 111);
			commentRequest.put("grade", "LOW");
			commentRequest.put("goods", "컴퓨터");
			commentRequest.put("comment", "컴퓨터가 안켜져요");
			s.doService(commentRequest);
		} catch(InvocationTargetException ie) {
			if(ie.getCause().getClass().equals(NullPointerException.class)) {
				log.debug("요청하신 주문번호에 대한 정보를 찾을 수 없습니다.");
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
