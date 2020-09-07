/**
 * 
 */
package kr.co.ant.study.vvooss;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.ant.study.reflect.spring.OrderController;
import kr.co.ant.study.reflect.spring.Request;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dev
 *
 */
@Slf4j
public class VosSpringLike {

	
	public static Map<String, Method> urlMethod;
	
	public static Object controller;
	
	public VosSpringLike(Class clazz)throws Exception {
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
		log.debug("create Controller class=["+clazz.getName()+"]and member controller's name=[]");
		clazz.cast(controller);

	}
	
	/**
	 * Reflection을 사용하여 RequestMapping Annotaion의 URL 정보와 해당 메소드 정보를 Map에 저장한다. Key = url(String), Value = Method 객체
	 * Class 내  Url 별로 메소드 정리
	 * 	 urlMethod.put(url, method);
	 *   Map<String, Methdo> ===> ["/order",saveOrder(메소드객체)];
	 */
	private void initUrlMethod(Class clazz)throws Exception {
		
		//
		// 클래스의 선언된 메소드를 탐색
		//
		for(Method m : clazz.getDeclaredMethods()) 
		{
			//
			// urlMethod는 리퀘스트만 따오는게 목적임. 다른 어노테이션은 무시함.
			//
			if(m.isAnnotationPresent(RequestMapping.class)) 
				log.debug("레퀘스트매핑어노테이션임");
			else 
				break;
			//
			// 해당 메소드에 어노테이션이 지정되었을 경우 어노테이션들을 탐색.
			// 
			for(Annotation a :m.getAnnotations()) 
			{
				
				//
				// 리퀘스트매핑 어노테이션 내의 선언된 메소드들을 탐색.
				//
				for(Method ma : a.getClass().getMethods()) 
				{
					String url = ""; // 어노테이션이 달고있는 값. url
					Object ob = null;// 어노테이션 인보크한 결과값. 
					//
					// 어노테이션의 선언된 메소드들을 인보크.
					// 리퀘스트 uri를 찾는 방법은 정규식을 이용해도 되겄으나,
					// 디폴트값인지 알수는 없지만, 뭐 쩔 수 없이 익셉션을 기대해봐야지. 
					// 우리는 value인지 path인지 어떤 메소드를 호출했을때 값이 나오는지 모름.
					// 그러나 리퀘스트매핑은 통상 유니크한 하나의 uri를 넣는다고 가정.
					// 값이 있으면 해당 값을 map에 put함. 1개만 하고 끝냄.
					// 해당 특정 메소드를 매핑하거나 선언되었으면 모르겠으나
					// requestmapping은 지랄맞음. 최소한 안전장치 isUrl 함수 만듦. 나중에 정확하게 검증하자. 
					// ㅇㅋ?
					//
					// log.debug("ma.names =["+ma.getName()+"]");
					
					try {
						// invoke
						ob = ma.invoke(a);
					} catch (Throwable e)  {
						log.info("["+ma.getName() + "]은 파라미터없이 호출했을때 에러발생함. 얘는 아님.");
					} finally {
						
						if(ob != null && ob.toString().length() > 0) 
						{
							// log.debug("retruntype is ["+ma.getReturnType().toString()+"]");
							// 리절트 object가 array 체크하고 매핑해야하오나, 바쁘다.
							String [] sarr = (String []) ob;
							if (ob.getClass().isArray() && isUrl(sarr[0]))
							{
								url = sarr[0];
								// urlMethod 구성.
								urlMethod.put(url, m);
								log.debug("here's method Name=["+m.getName()+"],ano name=["+a.getClass().getName() + "].method["+ma.getName()+"].value["+url+"]");
								break;
							}
						}
					}
				}
				
			}
		}
	}

	/**
	 * url인지 판별하는 허접한 메소드 추후에 정규식을 붙여보자.
	 * @param s
	 * @return
	 */
	boolean isUrl(String s) {
		log.debug("isURL??["+s+"]");
		if (s.startsWith("/")) return true;
		else return false;
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
		
		VosSpringLike s = new VosSpringLike(OrderController.class);
		
		Request req = new Request();
		req.setUrl("/order");
		req.put("num", "111");
		req.put("goods", "컴퓨터");
		req.put("qty", "2");
		req.put("deleveryStatus", "READY");
		/*
		 * s.doService(req);
		 * 
		 * Request deleveryStatusRequest = new Request();
		 * deleveryStatusRequest.setUrl("/order/deleveryStatus");
		 * deleveryStatusRequest.put("num", "111"); s.doService(deleveryStatusRequest);
		 * 
		 * s.doService(deleveryStatusRequest);
		 */
	}
}
