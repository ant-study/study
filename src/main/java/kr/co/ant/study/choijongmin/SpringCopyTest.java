/**
 * 
 */
package kr.co.ant.study.choijongmin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.ant.study.reflect.spring.OrderController;
import kr.co.ant.study.reflect.spring.Request;
import lombok.extern.slf4j.Slf4j;

/**
 * @author crono
 *
 */
@Slf4j
public class SpringCopyTest {
	public static Map<String, Method> urlMethod;

	public static Object controller;

	public SpringCopyTest(Class clazz) throws Exception {
		urlMethod = new HashMap<>();
		createController(clazz);
		initUrlMethod(clazz);
	}

	/**
	 * 생성자에서 넘겨 받은 Class에 대한 객체 생성하여 controller 변수에 저장
	 * @throws Exception
	 */
	private void createController(Class clazz) throws Exception {
		// controller = 객체생성
//		controller = clazz.getClass();
		Class<?> contClass = clazz;
		Constructor<?> constructor = contClass.getConstructor();
		controller = constructor.newInstance();
	}

	/**
	 * Reflection을 사용하여 RequestMapping Annotaion의 URL 정보와 해당 메소드 정보를 Map에 저장한다. Key = url(String), Value = Method 객체 Class 내 Url 별로 메소드 정리 urlMethod.put(url, method); Map<String, Methdo> ===>
	 * ["/order",saveOrder(메소드객체)];
	 */
	private void initUrlMethod(Class clazz) throws Exception {
		Method[] methods = clazz.getDeclaredMethods();

		for (Method method : methods) {
			RequestMapping mappingAnno = method.getAnnotation(RequestMapping.class);

			String[] urls = mappingAnno.value();
			for (int i = 0; i < urls.length; i++) {
				urlMethod.put(urls[i], method);
			}
		}
	}

	public void doService(Request request) throws Exception {
		// 입력된 URL 별로 메소드를 호출 한다.

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
	public Object toParameter(Request request, Class parameterType) throws Exception {
		Field[] fields =  parameterType.getDeclaredFields();
		Object vo = null;
		if ( fields.length > 0 ) {
			vo = parameterType.newInstance();
		} else {

		}
		 
		for (Field field : fields) {
			String fieldName = field.getName();
			String fieldValue = request.getParameters().get(field.getName());
			Class<?> fieldType = field.getType();
			
			Method[] methods = parameterType.getDeclaredMethods();
			for (Method method : methods) {
				String setMethodName = "set" + StringUtils.capitalize(fieldName);
				if (setMethodName.equals(method.getName())) {
					Method setMethod = parameterType.getMethod(setMethodName, fieldType);
					
					if ( int.class == fieldType) {
						setMethod.invoke(vo, Integer.parseInt(fieldValue));
					} else if (String.class == fieldType) {
						setMethod.invoke(vo, fieldValue);
					} else {
						Object[] objs = fieldType.getEnumConstants();
						for( int i=0; i<objs.length; i++) {
							String enumName = objs[i].toString();
							if ( enumName.equals(fieldValue) ) {
								setMethod.invoke(vo, objs[i]);
							}
						}
					}
				}
			}
		}
		
		return vo;
	}
	
	public static void main(String[] args) throws Exception {

		SpringCopyTest s = new SpringCopyTest(OrderController.class);

		Request req = new Request();
		req.setUrl("/order");
		req.put("num", "111");
		req.put("goods", "컴퓨터");
		req.put("qty", "2");
		req.put("deleveryStatus", "READY");
		s.doService(req);

//		Request deleveryStatusRequest = new Request();
//		deleveryStatusRequest.setUrl("/order/deliveryStatus");
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