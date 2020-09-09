package kr.co.ant.study.moonjonghun;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ClassUtils;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.ant.study.reflect.spring.Comment;
import kr.co.ant.study.reflect.spring.DeliveryStatus;
import kr.co.ant.study.reflect.spring.GoodsEvaluationGrade;
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
 * @author 문종훈
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
		//newInstance로 객체를 생성할때 클래스의 기본생성자를 호출한다.
		//클래스에 기본생성자가 포함되어있지 않더라도 컴파일시 자동적으로 기본생성자를 생성해준다.
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
		Method[] methods = clazz.getDeclaredMethods();
		
		for(Method method : methods) {
			Annotation anno = method.getAnnotation(RequestMapping.class);
			String[] paths =((RequestMapping) anno).value();	

			log.info("path Value : {}", paths);
			
			//request요청 경로가 여러개일수 있지만 첫번째 경로만 가져오도록 한다.
			urlMethod.put(paths[0], method);
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
		
		Class requestClazz = request.getClass();
		Method[] requestMethods = requestClazz.getDeclaredMethods();
		
		for(Method reqMethod : requestMethods) {
			if(reqMethod.getName() == "getParameters") {
				Map<String, String> map =(Map<String, String>) reqMethod.invoke(request);
				
				log.info("class name : {}",parameterType.getName());
				
				Class paramClsTyp = ClassUtils.resolvePrimitiveIfNecessary(parameterType);
				
				
				//매개변수가 Order 혹은  Comment 라면
				if(Order.class.isAssignableFrom(paramClsTyp) || Comment.class.isAssignableFrom(paramClsTyp)) {
					
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
							int paramVal = (int) NumberUtils.parseNumber(map.get(fd.getName()), Integer.class);	
							getMethod.invoke(obj, paramVal);
						} else if(fd.getType().isEnum()){
							if(parameterType.equals(Order.class)) {
								DeliveryStatus paramVal = DeliveryStatus.valueOf(map.get(fd.getName()));
								getMethod.invoke(obj, paramVal);
							} else {
								GoodsEvaluationGrade paramVal = GoodsEvaluationGrade.valueOf(map.get(fd.getName()));
								getMethod.invoke(obj, paramVal);
							}
						} else {
							String paramVal = map.get(fd.getName());
							getMethod.invoke(obj, paramVal);
						}
					}
					
					return obj;
				}
				
				//매개변수가 int 라면
				if(Number.class.isAssignableFrom(paramClsTyp)) {
					int num = NumberUtils.parseNumber(map.get("num"), Integer.class);
					return num;
				}
				
			}
		}
		
		throw new Exception("오류");
	}
	
	
	public static void main(String[] args) {
		try {
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
			
			Request commentRequest = new Request();
			commentRequest.setUrl("/goods/comment");
			commentRequest.put("num", "111");
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
