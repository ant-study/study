package kr.co.ant.study.seomyeongjoo.generic;

import kr.co.ant.study.reflect.spring.Comment;
import kr.co.ant.study.reflect.spring.Order;
import org.apache.commons.beanutils.BeanUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 메소드 안 내용은 변수명 포함해서 얼마든지 변경 해도 됩니다.
 * Generic을 사용해서  형변환은 1도 들어가면 안됩니다. 
 * @author hankk
 *
 */
public class Convertor {


	/**
	 * Map을 받아서 Map을 JavaBean으로 변환(VO, DTO)
	 * @param map
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <V, T> T toVO(Map<String, V> map, Class<T> clazz) throws Exception{
		Iterator<String> s = map.keySet().iterator();
		T o = clazz.newInstance();
		while(s.hasNext()) {
			String key = s.next();
			V obj = map.get(key);
			BeanUtils.setProperty(o, key, obj);
		}
		return o;
	}

	
	public static void main(String[] args) throws Exception {
		Map<String, Object> m = new HashMap<>();
		m.put("num", "111");
		m.put("goods", "goods");
		m.put("qty", "2");

		Order o = toVO(m, Order.class);
		//Order o = (Order) toVO(m, Order.class);
		System.out.println(o.getGoods()+", "+o.getNum()+", "+o.getQty());
		
		
		Map m2 = new HashMap();
		m2.put("num", "111");
		m2.put("goods", "goods");
		m2.put("comment", "2");
		//m2.put("grade", "MIDDLE");
		
		Comment c = toVO(m2, Comment.class);
		System.out.println(c.getGoods()+", "+c.getNum()+", "+c.getComment());
	}
	
}
