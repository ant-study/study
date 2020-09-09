package kr.co.ant.study.songyoona.annotation.ex2;

import java.lang.annotation.Annotation;
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
public class SpringCopy {


	public static Map<String, Method> urlMethod;

	public static Object controller;

	public SpringCopy(Class clazz)throws Exception {
		urlMethod = new HashMap<String, Method>();
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

	    //controller = clazz.getClass();
	    //this.controller = clazz.getConstructors();
	    controller = clazz.newInstance();

	    log.debug("controller: "+controller.getClass().getName());

	}

	/**
	 * Reflection을 사용하여 RequestMapping Annotaion의 URL 정보와 해당 메소드 정보를 Map에 저장한다.
	 * Key = url(String), Value = Method 객체
	 * Class 내  Url 별로 메소드 정리
	 * 	 urlMethod.put(url, method);
	 *   Map<String, Methdo> ===> ["/order",saveOrder(메소드객체)];
	 */
	private void initUrlMethod(Class clazz)throws Exception {

	    for(Method m : clazz.getDeclaredMethods()) {

	        for(Annotation a : m.getAnnotations()) {
	            if (a.annotationType()== RequestMapping.class) {

	                RequestMapping re = (RequestMapping) a;
	                String[] urls = re.value();
	                for(String url : urls) {
	                    log.debug("urlMethod.put::: "+url + " method: "+m);
	                    urlMethod.put(url, m);
	                }
	            }
	        }
	    }
	}


	public void doService(Request request) throws Exception{
		//입력된 URL 별로 메소드를 호출 한다.

		Method method = urlMethod.get(request.getUrl());

		Class parameterType = method.getParameterTypes()[0];
		// parameterType = Order.class

		Object argument = toParameter(request, parameterType);
		method.invoke(controller, argument);

	}

	/**
	 * request에 파라미터 값을 넘겨받은 Class의 객체를 생성하여 값을 셋팅하여 리턴한다.
	 * @param request
	 * @param parameterType
	 * @return paraeterType 객체
	 * 숫자형은 NumberUtils.parseNumber 사용
	 */
	public Object toParameter(Request request, Class parameterType)throws Exception {
	    Object obj = parameterType.newInstance();

	    //request는  form에서 받아온 데이터들 이라고 생각.
	    //Map<String, String> order = request.getParameters();

	    Field[] rf = request.getClass().getDeclaredFields();
	    for(Field rfield : rf) {
            log.debug("필드이름: "+ rfield.getName() + " type: "+ rfield.getType());
            // request field가 Map일때
            if(rfield.getType() == Map.class) {
                log.debug("map이다");
                String methodNm= "get"+StringUtils.capitalize(rfield.getName());
                Method reqMethod = request.getClass().getMethod(methodNm);
                // request Map 값을 가져오는 method : map 에 담아둠
                Map<String, String> map = (Map<String, String>) reqMethod.invoke(request);

                // parameterType field에 set작업
                Field[] pf = parameterType.getDeclaredFields();
                for(Field paramf : pf) {
                    log.debug("param 필드이름: "+ paramf.getName() + " type: "+ paramf.getType());

                    String methodName = "set"+StringUtils.capitalize(paramf.getName());

                    //Class의 메소드 조회 인자값으로 Field의 Type 넘겨줌
                    Method method = parameterType.getMethod(methodName, paramf.getType());

                   // method.invoke(obj, paramf.getType());
                    if (paramf.getType().getName() == "int") {
                        method.invoke(obj, NumberUtils.parseNumber(map.get(paramf.getName()), Integer.class));
                    } else {
                        if (paramf.getType().isEnum()) {   //  enum
                            method.invoke(obj, Enum.valueOf((Class<Enum>) paramf.getType(), map.get(paramf.getName())));

                        } else {
                            method.invoke(obj, map.get(paramf.getName()));
                        }
                    }
                }
            }

        }
	    return obj;
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
//        deleveryStatusRequest.setUrl("/order/deleveryStatus");
//        deleveryStatusRequest.put("num", "111");
//        s.doService(deleveryStatusRequest);

        Request commentRequest = new Request();
        commentRequest.setUrl("/goods/comment");
        commentRequest.put("num", "111");
        commentRequest.put("grade", "LOW");
        commentRequest.put("goods", "컴퓨터");
        commentRequest.put("comment", "컴퓨터가 안켜져요");
        s.doService(commentRequest);


	}
}
