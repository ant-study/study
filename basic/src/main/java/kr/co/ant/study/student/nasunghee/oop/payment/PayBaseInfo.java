package kr.co.ant.study.student.nasunghee.oop.payment;

import java.util.function.Supplier;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayBaseInfo {
	
	private String productId;
	private String productName;
	private String amount;
	private PaymentType paymentType;
	
//	public PayBaseInfo(Supplier<? extends PaymentType> dicFactory) {
//		this.paymentType = dicFactory.get(); 
//	}

}
