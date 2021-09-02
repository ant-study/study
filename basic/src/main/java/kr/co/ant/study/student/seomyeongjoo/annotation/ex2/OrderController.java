package kr.co.ant.study.student.seomyeongjoo.annotation.ex2;



import kr.co.ant.study.reflect.spring.Comment;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

public class OrderController {
	
	public static Map<Integer, Order> orders;
	
	static {
		orders = new HashMap<>();
	}
	@RequestMapping("/order")
	public void saveOrder(Order order) {
		orders.put(order.getNum(), order);
		System.out.println(String.format("주문하신 [%s] 상품에 수량 [%s] 대해 주문이 접수 되었습니다. => 주문번호 [%s]", order.getGoods(), order.getQty(), order.getNum()));
	}

	@RequestMapping("/order/deliveryStatus")
	public void searchDeleveryStatus(int num) {
		Order order = orders.get(num);
		System.out.println(String.format("주문하신 [%s] 상품에 배송상태 [%s]", order.getGoods(), order.getDeleveryStatus().getValue()));
	}

	@RequestMapping("/goods/comment")
	public void writeGoodsComment(Comment comment) {
		System.out.println(String.format("주문하신 [%s] 상품에 평가 등급 [%s] => [%s], ", comment.getGoods(), comment.getGrade(), comment.getComment()));
	}
}
