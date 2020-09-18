package kr.co.ant.study.seomyeongjoo.oop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class PaymentInfo {

	private String productId;
	private String productName;
	private long amount;
	private String paymentType;

	private CardInfo cardInfo;
	private MobileInfo mobileInfo;
	private BankAccountInfo bankAccountInfo;


}
