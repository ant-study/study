package kr.co.ant.study.student.nasunghee;

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

//	우리가 하는게 Spring 구조에서 HandlerMapping을 역할할거를 만드는것같은데,
//	그게 컨트롤러 찾아가고
//	컨트롤러 안에 맞는 메서드 찾아가고 파라미터 set해주는거
	
//	숫자형 변환은 NumberUtils.parseNumber 요거 씁시다
	
	
	
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
		//clazz = OrderController 
		
		//생성자에서 넘겨 받은 Class = clazz = OrderController
//		Class c = this.getClass(); 			//SpringCopy 
		
		Constructor[] cs = clazz.getConstructors(); //SpringCopy(){}
		for (int i = 0; i < cs.length; i++) {
			
//			controller =cs[i]; //OrderController() 
			controller = cs[i].newInstance();  //OrderController()

		}
		
	}
	
	/**
	 * Reflection을 사용하여 RequestMapping Annotaion의 URL 정보와 해당 메소드 정보를 Map에 저장한다. Key = url(String), Value = Method 객체
	 * Class 내  Url 별로 메소드 정리
	 * 	 urlMethod.put(url, method);
	 *   Map<String, Methdo> ===> ["/order",saveOrder(메소드객체)];
	 */
	private void initUrlMethod(Class clazz)throws Exception {
//		System.out.println(controller); //OrderController()
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			Annotation[] annotations = method.getDeclaredAnnotations();
			for (Annotation anno : annotations) {
//				System.out.println(anno.annotationType()); //interface org.springframework.web.bind.annotation.RequestMapping
				if(anno instanceof RequestMapping) {
					String url = ((RequestMapping) anno).value()[0]; // /order  /goods/comment	/order/deliveryStatus
					urlMethod.put(url, method);
				}
			}
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
		
		//request... 안에 url이랑 parameters안에 map<key,value>포함
		//parameterType은 >> kr.co.ant.study.reflect.spring.Order
		//request 안에 map<k,v>로 저장된 값을 parameterType 클래스 생성해서 setter로 지정해줄까?
		//일단 parameterType으로 클래스를 생성해보자
		Object o = parameterType.newInstance();
		//Method[] methods = parameterType.getDeclaredMethods();
		Field[] fields = parameterType.getDeclaredFields();
				
		
		//request의 parameter를... 어케 가져오나
		Map<String,String> paramMap = request.getParameters(); 
		//System.out.println(paramMap); //{num=111, qty=2, deleveryStatus=READY, goods=컴퓨터}
		
		//paramMap의 key는 Order의 field,
		//paramMap의 value는 string값의 field값 >> num - int, goods - string, qty - int, deleverystatus - DeliveryStatus(Enum)
		Object setParam = new Object();
		for (Field field : fields) {
			String methodName = "set"+StringUtils.capitalize(field.getName());
			Method method = o.getClass().getMethod(methodName, field.getType());

			if(field.getType() == int.class) {
				Integer param = NumberUtils.parseNumber(paramMap.get(field.getName()), Integer.class);
				setParam = param;
			}else if(field.getType() == String.class) {
				setParam = paramMap.get(field.getName());
			}else if(field.getType().isEnum()) {
				//Class clzz = field.getType().getSuperclass().getClass();
//				Class clzz = Class.forName(field.getType().getName());
				//clzz.newInstance();		// >> 추상클래스 혹은 인터페이스는 newInstance생성 못하는데
//				Method m = clzz.getDeclaredMethod("getValue"); >> 사실 DelivertStatus의 getValue를 가지고 오려고 했으나....
				
				Object[] enos = field.getType().getEnumConstants(); //Object는 DeliveryStatus여야..
				for (Object eno : enos) {
					if(eno.toString().equals(paramMap.get(field.getName()))) {
						setParam = eno;
					}
				}
			}
			method.invoke(o, setParam);
			
		}
		return o;
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
		
//		Request deleveryStatusRequest = new Request();
//		deleveryStatusRequest.setUrl("/order/deleveryStatus");
//		deleveryStatusRequest.put("num", "111");
//		s.doService(deleveryStatusRequest);
		
		Request commentRequest = new Request();
		commentRequest.setUrl("/goods/comment");
		commentRequest.put("num", "111");
		commentRequest.put("grade", "LOW");
		commentRequest.put("goods", "컴퓨터");
		commentRequest.put("comment", "컴퓨터가 안켜져요");
		s.doService(commentRequest);
		
	}
}
