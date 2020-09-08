/**
 * 
 */
package kr.co.ant.study.vvooss;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.ant.study.reflect.spring.DeliveryStatus;
import kr.co.ant.study.reflect.spring.OrderController;
import kr.co.ant.study.reflect.spring.Request;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 솔직히 나 학교다닐때 자바 첫 수업때 교수님이 수업듣지말라고했음..
 * 그게 자바 기초였는데, OOP 패러다임 쉬프트는 난 이미 끝났었거든..
 * 
 * 그래서 기초수업 안듣고 다음학기 자바 프로젝트 실습하는거 애플릿으로 만들라카데..
 * 
 * 남들은 채팅프로그램 같은거 만들었는데,
 * 우리팀은 운전면허 시뮬 프로그램 만들었는데,
 * 
 *  동그란 핸들 오브젝트 만들다 끝났지 뭐야. 카트처럼 하면 간단했을텐데..
 * 
 * 그때 랩실에서 살았거던...숙제하면서 그때 기억남..
 * 
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
		
		// 피곤함..
		// casting 해주면 좋을 것같은데 자바는 자바스크립트가 아님..
		// log.debug("create Controller class=["+clazz.getName()+"]and member controller's name=[]");
		// clazz.cast(controller);
		
		
		// 어떻게 생성하냐? 컨스트럭터 불러옴. 
		// 보통한개이고 보통 파라미터없는데, 이상하게 필수파라미터 던져달라는 생성자들 있어.
		// 그런 이상한 애들 친절하게 예외처리랑 다 받아주는건 나중에 방학때 하자..
		// 빨래 다돌아가고익성.
		// Constructor c = clazz.getConstructors();
		
		for (Constructor c : clazz.getConstructors()) {
			log.debug("얘는 생성자몇개냠?   [ "+clazz.getConstructors().length +"  ] ");
			this.controller = c.newInstance();
			log.debug("컨트롤러 너 이름뭐냐  개명했냐?? [ " + controller.getClass().getName()+"]");
		}
		
		// 끝났으면 좋겠다.. 확인해보자.
		for (Method m : controller.getClass().getMethods())
			log.debug("controller 정신차렷!!["+m.getName()+"]");
		
		log.debug("좋아. 끄덕끄덕. 이제 너의 타입은 그거야.");

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
								log.debug("URLMEHTOD.GET=["+urlMethod.get(url));;
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
		log.debug("doSERVICE strt!");
		Method method = urlMethod.get(request.getUrl());
		log.debug("METHOD=["+method.getName());
		Class parameterType = method.getParameterTypes()[0];
		log.debug("ParamType=["+parameterType.getName());
		Object argument = toParameter(request, parameterType);
		
		method.invoke(controller, argument);
		
	}
	
	/**
	 * request에 파라미터 값을 넘겨받은 Class의 객체를 생성하여 값을 셋팅하여 리턴한다.
	 * 
	 * 한차장님은 집요하기도 하지.. 위에서 생성자로 생성하는거 했는데, set 하래..
	 * set하는 거는 첫 숙제였자나.. 그거 또 해야해?....?? 
	 * 아하 이넘이 익네?,,, 
	 * 
	 * 하,,, 맵이네? 아,,, 맵을 클래스로 바꾸라고...
	 * 
	 * @param request
	 * @param parameterType
	 * @return paraeterType 객체
	 */
	public Object toParameter(Request request, Class parameterType)throws Exception {
		
		Class prmType = parameterType;
		//log.debug(prmType.isInstance(new Object()));
		// 생성자로 오브젝트 생성하자.
		// 이정도 편법은 봐줘요 쌤.. 
		//if (parameterType instanceof Object)
		Object yesItsMeHoney = null;
		
	
		yesItsMeHoney = parameterType.newInstance();
		
		log.debug("ma honeys name ["+yesItsMeHoney.getClass().getName()+"]");
		
		// request 에는 hashmap에 풋되어있다.
		// map.get("key");
		// key는 ? 
		// parameterType의 필드명이다.
		
		for(Field pf : prmType.getDeclaredFields())
		{
			// field copy
			this.copyFields(request, yesItsMeHoney, pf);
			// member copy
			log.debug("class cnt=["+request.getClass().getDeclaredClasses().length+"]");
			
		}
		
	
		return yesItsMeHoney;
		
		
	}
	
	// key로만 비교하여 같은 이름이면 카피한다.
	void copyFields (Object targ, Object dest, Field fld) throws Exception{
		
		VOSReflect vos = new VOSReflect();
		
		DeliveryStatus test;
		
		// DeliveryStatus.valueOf("DELIVERING");
		log.debug(DeliveryStatus.valueOf("DELIVERING").getValue());
		String fieldNm = fld.getName();
		log.debug("copyFields fldNm=["+fld.getName()+"]");
		if (!fld.isAccessible()) fld.setAccessible(true);
		
		for (Field f : targ.getClass().getDeclaredFields()) {
		
			log.debug("f field Name=["+f.getName()+"]");
			//for (Field df : dest.getClass().getDeclaredFields())
			if (f.getName().equals(fieldNm) && f.getType() == dest.getClass().getField(fieldNm).getType())
			{
				//matching 
				vos.setValue(dest,vos.getValue(targ, f.getName()),f.getName());
				//log.debug(vos.getValue(dest, fieldNm).toString());
			}
			
			// 맵임?
			f.setAccessible(true);
			
			
			//Object oVal = vos.getValue(f.get(targ), f.getName());
			
			if (f.get(targ) != null && f.get(targ) instanceof Map) 
			{
				log.debug("checking"+f.get(targ));
				Object mapVal="";;
				for (Method cf : f.getType().getDeclaredMethods()) 
				{
					if (cf.getName().equals("get"))
					{
						try 
						{
							mapVal = cf.invoke(f.get(targ),fieldNm);
						}
						catch (Exception e) 
						{
							log.info("그런멤버없대["+fieldNm+"]");
						}
						
						log.debug("dest name=["+dest.getClass().getName()+"],mapKey=["+fieldNm+"],mapValue=["+mapVal+"]");
						//fld.set(dest, f.getType().cast(mapVal));
						vos.setValue(dest,mapVal,fieldNm);
					}
				}
			}
		
		}
			
	}

	
	public static void main(String[] args)throws Exception {
		
		VosSpringLike s = new VosSpringLike(OrderController.class);
		
		Request req = new Request();
		req.setUrl("/order");
		req.put("num", "111");
		req.put("goods", "컴퓨터");
		req.put("qty", "2");
		req.put("deleveryStatus", "READY");
		
		s.doService(req);
		/*
		 * Request deleveryStatusRequest = new Request();
		 * deleveryStatusRequest.setUrl("/order/deliveryStatus");
		 * deleveryStatusRequest.put("num", "111");
		 * log.debug("CHK"+deleveryStatusRequest.getUrl());
		 * s.doService(deleveryStatusRequest);
		 */
		  //s.doService(deleveryStatusRequest);
		 
	}
}
