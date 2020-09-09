package kr.co.ant.study.imsoyeon.third;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ClassUtils;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.ant.study.reflect.spring.DeliveryStatus;
import kr.co.ant.study.reflect.spring.OrderController;
import kr.co.ant.study.reflect.spring.Request;

public class SpringCopy {
	
public static Map<String, Method> urlMethod;
	
	public static Object controller;
	
//	Constructor
	public SpringCopy(Class clazz) throws Exception {
		urlMethod = new HashMap<>();
		
//		HandlerMapping 1. Which Controller
		createController(clazz);
		
//		HandlerMapping 1. Which Mapping Method
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
		
//		Need : url & methodName
		Method[] methods = clazz.getDeclaredMethods();
		
		for (Method method : methods) {
			Annotation[] annotations = method.getAnnotations();
			
			for (Annotation annotation : annotations) {
				if (annotation instanceof RequestMapping) {
					RequestMapping rq = (RequestMapping) annotation;
					
					String[] urls = rq.value();
					for (String url : urls) {
						String methodName = method.getName();
						Class[] parameterType = method.getParameterTypes();
						urlMethod.put(url, clazz.getMethod(methodName, parameterType));
					}
				}
			}
		}
	}

	
	public void doService(Request request) throws Exception{
		//입력된 URL 별로 메소드를 호출 한다.
		
		Method method = urlMethod.get(request.getUrl());
		
//		Mapping Method의 parameter's types
		Class parameterType = method.getParameterTypes()[0];
		
//		Request's param → to VO Class
		Object argument = toParameter(request, parameterType);
		
//		Servlet 완성
//		method info + parameter for mathod => call method
		method.invoke(controller, argument);
		
	}
	
	/**
	 * request에 파라미터 값을 넘겨받은 Class의 객체를 생성하여 값을 셋팅하여 리턴한다.
	 * @param request
	 * @param parameterType
	 * @return paraeterType 객체
	 */
	public Object toParameter(Request request, Class parameterType) throws Exception {
//		request data → VO data
		
//		1.Get Request's parameter(type Map)
//		parameters값만 넣음
		Map<String, String> map = (Map<String, String>) getReqParam(request);		
		
//		2.Set MappingMethod's parameter
		Object obj = setParameter(parameterType, map);
		
		return obj;
	}
	
	/**
	 * Request의 Parameter 가져오기
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Object getReqParam(Object request) throws Exception {
		
		Class clazz = request.getClass();
		Field[] fields = clazz.getDeclaredFields();
		
		String methodNm = null;
		for (Field field : fields) {
			field.setAccessible(true);
			
			if ("parameters".equals(field.getName())) {		//다른 방법 없어?
				methodNm = "get" + StringUtils.capitalize(field.getName());				
			}
		}
		Method method = clazz.getMethod(methodNm);
		
		return method.invoke(request);
	}
	
	/**
	 * 매핑메소드의 Parameter 세팅
	 * 
	 * @param vo
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public Object setParameter(Class vo, Map<String, String> req) throws Exception {
//		Class 말고 Object 써야지
		Object obj = vo.newInstance();
		
		Field[] fields = vo.getDeclaredFields();
		for (Field field : fields) {
			String methodNm = "set" + StringUtils.capitalize(field.getName());
			String fieldNm = field.getName();
			
			Method method = vo.getMethod(methodNm, field.getType());
			
//			casting 어떻게 할까		paramType.cast(value) 이거 안먹혀
			
			Class wrapperClass =ClassUtils.resolvePrimitiveIfNecessary(field.getType());
			
			wrapperClass.isAssignableFrom(Number.class);
			
			if(Number.class.isAssignableFrom(wrapperClass)) {
				NumberUtils.parseNumber(req.get(fieldNm), wrapperClass);
			}else {
				if (field.getType().isEnum()) {	//	enum
					method.invoke(obj, Enum.valueOf((Class<Enum>) field.getType(), req.get(fieldNm)));
				} else {	//not enum
					method.invoke(obj, req.get(fieldNm));
				}
			}
				
		}
		
		return obj;
	}

	public static void main(String[] args)throws Exception {
		
//		객체 생성
		SpringCopy s = new SpringCopy(OrderController.class);
		
//		Request setting
		Request req = new Request();
		req.setUrl("/order");
		req.put("num", "111");
		req.put("goods", "컴퓨터");
		req.put("qty", "2");
		req.put("deleveryStatus", "READY");
		
		s.doService(req);
		
		Request deleveryStatusRequest = new Request();
//		deleveryStatusRequest.setUrl("/order/deliveryStatus");
//		deleveryStatusRequest.put("num", "111");
		
//		s.doService(deleveryStatusRequest);
		
		deleveryStatusRequest.setUrl("/goods/comment");
		deleveryStatusRequest.put("num", "111");
		deleveryStatusRequest.put("goods", "컴퓨터");
		deleveryStatusRequest.put("grade", "HIGH");
		deleveryStatusRequest.put("comment", "좋아요~");
		
		s.doService(deleveryStatusRequest);
		
	}
}
