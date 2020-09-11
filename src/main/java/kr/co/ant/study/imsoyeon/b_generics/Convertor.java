package kr.co.ant.study.imsoyeon.b_generics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import kr.co.ant.study.reflect.spring.Comment;
import kr.co.ant.study.reflect.spring.Order;
import lombok.extern.slf4j.Slf4j;

/**
 * 메소드 안 내용은 변수명 포함해서 얼마든지 변경 해도 됩니다.
 * Generic을 사용해서  형변환은 1도 들어가면 안됩니다. 
 * @author hankk
 *
 */
@Slf4j
public class Convertor {
	
	/**
	 * Map을 받아서 Map을 JavaBean으로 변환(VO, DTO)
	 * @param map
	 * @param clazz
	 * @return 
	 * @return
	 * @throws Exception
	 */
//	Q1. 왜 static이 꼭 붙어여할까?
//			→ main 메소드에서 new Convertor() 안하려고
//			→ 객체생성을 하지 않고도 메소드에 접근해서 호출할 수 있다
//			→ 컴파일시 static은 먼저 메모리에 생성됨 	(	정적 메모리에 올라간다는데 그게 어디? Heap? life cycle : 프로그램 시작&끝)
//			→ non-static(인스턴스 변수)는 소속 클래스의 객체가 생성될 때, 생성됨 	(	Stack에 생성 life cycle : 객체랑 같이	)  
//			→ static 메소드 안에서는 non-static 메소드는 객체 생성없이 사용할 수 없음. 없으니까?
	
//	Class 2개를 제네릭 인자로 넣을 수 없음. Class는 1개, Interface는 n개 설정 가능
	public static <T extends Object>T toVO(Map map, Class<T> clazz) throws Exception{
		T obj = clazz.newInstance();
		
		Iterator<Map.Entry> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = iterator.next();
			log.info("\n");
			log.info("#########\tEntry KEY : {}, VALUE : {}", entry.getKey(), entry.getValue());
			BeanUtils.setProperty(obj, entry.getKey(), entry.getValue());
		}
		return obj;
	}
	
	public static void main(String[] args) throws Exception {
		Map m = new HashMap();
		m.put("num", "111");
		m.put("goods", "goods");
		m.put("qty", "2");
		
		Order o = toVO(m, Order.class);
		System.out.println(o.getGoods()+", "+o.getNum()+", "+o.getQty());
		
		
		Map m2 = new HashMap();
		m2.put("num", "111");
		m2.put("goods", "goods");
		m2.put("comment", "2");
//		m2.put("grade", "MIDDLE");
		
		Comment c = toVO(m2, Comment.class);
		System.out.println(c.getGoods()+", "+c.getNum()+", "+c.getComment());
	}
	
}
