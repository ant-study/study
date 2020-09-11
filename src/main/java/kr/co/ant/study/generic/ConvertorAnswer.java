package kr.co.ant.study.generic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import kr.co.ant.study.reflect.spring.Comment;
import kr.co.ant.study.reflect.spring.Order;

public class ConvertorAnswer {
	
	public static <T> T toVO(Map<String, ?> map, Class<T> clazz) throws Exception{
		Iterator<String> s = map.keySet().iterator();
		T t = clazz.newInstance();
		while(s.hasNext()) {
			String key = s.next();
			BeanUtils.setProperty(t, key, map.get(key));
		}
		return t;
	}
	
	public static void main(String[] args) throws Exception {
		Map<String, Object> m = new HashMap<>();
		m.put("num", "111");
		m.put("goods", "goods");
		m.put("qty", "2");
		
		Order o = toVO(m, Order.class);
		System.out.println(o.getGoods()+", "+o.getNum()+", "+o.getQty());
		
		
		Map<String, Object> m2 = new HashMap<>();
		m2.put("num", "111");
		m2.put("goods", "goods");
		m2.put("comment", "2");
		
		Comment c = toVO(m2, Comment.class);
		System.out.println(c.getGoods()+", "+c.getNum()+", "+c.getComment());
	}
	
}
