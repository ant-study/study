package kr.co.ant.study.nasunghee;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import kr.co.ant.study.reflect.spring.Comment;
import kr.co.ant.study.reflect.spring.Order;

/**
 * 메소드 안 내용은 변수명 포함해서 얼마든지 변경 해도 됩니다.
 * Generic을 사용해서  형변환은 1도 들어가면 안됩니다. 
 * @author hankk
 *
 */
public class Convertor {
	
	/**
	 * Map을 받아서 Map을 JavaBean으로 변환(VO, DTO)
	 * @param <T>
	 * @param <T>
	 * @param map
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T toVO(Map<String, ?> map, Class<T> clazz) throws Exception{
		Iterator<String> s = map.keySet().iterator();
		T o = clazz.newInstance();		
		while(s.hasNext()) {
			String key = s.next();			// next()는 변수에 담아서 사용.
			BeanUtils.setProperty(o, key, map.get(key));				//>>> 형변환 value값을 해당 필드에 선언된 자료형으로 넣어준다.
			//getProperty >>>	map.get(key) = BeanUtils.getProperty(o,key);
		}
		return o;
	}
	
	public static void main(String[] args) throws Exception {
		Map<String, String> m = new HashMap<>();	//노란줄 없애려면....?
		m.put("num", "111");
		m.put("goods", "goods");
		m.put("qty", "2");
		
		Order o = toVO(m, Order.class);									//>>> 형변환 toVo(m,Order.class).. return을T
		System.out.println(o.getGoods()+", "+o.getNum()+", "+o.getQty());
		
		
		Map<String,String> m2 = new HashMap<>();
		m2.put("num", "123");
		m2.put("goods", "good goods");
		m2.put("comment", "조아요");
		
		Comment c = toVO(m2, Comment.class);							//>>> 형변환
		System.out.println(c.getGoods()+", "+c.getNum()+", "+c.getComment());
	}
	
}
