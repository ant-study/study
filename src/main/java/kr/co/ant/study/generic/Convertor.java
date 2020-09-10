package kr.co.ant.study.generic;

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
	 * @param map
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static Object toVO(Map map, Class clazz) throws Exception{
		Iterator s = map.keySet().iterator();
		Object o = clazz.newInstance();
		while(s.hasNext()) {
			Object key = s.next();
			BeanUtils.setProperty(o, (String)key, map.get(key));
		}
		return o;
	}
	
	public static void main(String[] args) throws Exception {
		Map m = new HashMap();
		m.put("num", "111");
		m.put("goods", "goods");
		m.put("qty", "2");
		
		Order o = (Order) toVO(m, Order.class);
		System.out.println(o.getGoods()+", "+o.getNum()+", "+o.getQty());
		
		
		Map m2 = new HashMap();
		m2.put("num", "111");
		m2.put("goods", "goods");
		m2.put("comment", "2");
		
		Comment c = (Comment) toVO(m2, Comment.class);
		System.out.println(c.getGoods()+", "+c.getNum()+", "+c.getComment());
	}
	
}
