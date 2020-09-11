package kr.co.ant.study.mkhan.generic;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

import kr.co.ant.study.reflect.spring.Comment;
import kr.co.ant.study.reflect.spring.Order;

/**
 * 메소드 안 내용은 변수명 포함해서 얼마든지 변경 해도 됩니다.
 * Generic을 사용해서  형변환은 1도 들어가면 안됩니다. 
 * @author hankk
 *
 */
public class Convertor {
	
//	private static String key;
	
	/**
	 * Map을 받아서 Map을 JavaBean으로 변환(VO, DTO)
	 * @param <K>
	 * @param map
	 * @param clazz
	 * @return 
	 * @return
	 * @throws Exception
	 */
	//public static Object toVO(Map map, Class clazz) throws Exception{
	/**
	 * E : 요소 (Element)
	 * K : 키 (Key)
	 * N : 슷자 (Number)
	 * T : 타입 (Type)
	 * V : 값 (Value)
	 * S, U, V : 두번째, 세번째, 네번째에 선언된 타입
	 **/
	public static <T> T toVO(Map map, Class<T> clazz) throws Exception{
		
		/** iterator
		 * . 정의
			   - Java에서 제공하는 컬렉션(Collection)객체는 보관하고 있는 자료들을 순차적으로 접근하면서 처리할 때 사용하는 Iterator 형식을 제공하고 있다.
			   컬렉션 객체 : List, Set, Map, Queue 등
			   - Iterator는 반복자라고 부르며 컬렉션 종류에 관계없이 같은 방법으로 프로그래밍 할 수 있게 해 준다.
			2. 사용방법
			   - Iterator 개체는 컬렉션 개체의 iterator() 메서드를 호출하여 얻어올 수 있다.
			   - hasNext() 메서드로 이동이 가능한지 확인한 후에 next() 메서드로 해당 위치의 보관한 개체를 참조하여 원하는 작업을 수행한다.
			3. 장.단점
			   - 이와 같은 Iterator 개체를 사용하면 size 메서드를 얻어와서 반복 처리하는 것보다 속도에서 불리하다.
			   - 이는 Iterator 개체를 사용하는 부분이 있기 때문에 불가피한 사항이다.
				  하지만 컬렉션 종류에 관계없이 일관성있게 프로그래밍할 수 있다는 장점을 갖고 있다.
			   - 소스 코드에 어떠한 컬렉션을 사용할 지 정해지지 않았지만 컬렉션 내에 보관한 모든 내용을 출력하는 등의 작업을 먼저 하길 원한다면 Iterator를 사용하는 것은 좋은 선택이다.
		 */
		
//		Iterator s = map.keySet().iterator();
		Iterator<String> s = map.keySet().iterator();
		
		 T t = clazz.newInstance();
		 
		 //final String 때문에 ... 어떻게 해야할까..?
		 while(s.hasNext()) {
			 String key = s.next();
			 BeanUtils.setProperty(t, key, map.get(key));
			 //s.remove(); // next()에 나온 값을 list에서 삭제함
		 }
		 
		
//		while(s.hasNext()) {
//			Object key = s.next();
//			Object value = map.get(key);
//			BeanUtils.setProperty(o, key, value);
//		}
		
		return t;
	}
	
	public static void main(String[] args) throws Exception {
		
		Map m = new HashMap();
		m.put("num", "111");
		m.put("goods", "goods");
		m.put("qty", "2");
		
		Order o =  toVO(m, Order.class);
		System.out.println(o.getGoods()+", "+o.getNum()+", "+o.getQty());
		
		
		Map m2 = new HashMap();
		m2.put("num", "111");
		m2.put("goods", "goods");
		m2.put("comment", "2");
//		m2.put("grade", "MIDDLE"); ??
		
		Comment c = toVO(m2, Comment.class);
		System.out.println(c.getGoods()+", "+c.getNum()+", "+c.getComment());
	}
	
}
