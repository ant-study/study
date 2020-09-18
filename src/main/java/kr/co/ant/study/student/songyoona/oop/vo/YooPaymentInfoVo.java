package kr.co.ant.study.student.songyoona.oop.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class YooPaymentInfoVo {

	private String productId;

	private String productName;

	private long amount;

	private String paymentType;

	private CardInfo cardInfo;
	private BankInfo bankInfo;
	private MobileInfo mobileInfo;
}
