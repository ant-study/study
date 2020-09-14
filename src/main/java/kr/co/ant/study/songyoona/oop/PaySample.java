package kr.co.ant.study.songyoona.oop;

import kr.co.ant.study.songyoona.oop.vo.BankInfo;
import kr.co.ant.study.songyoona.oop.vo.CardInfo;
import kr.co.ant.study.songyoona.oop.vo.MobileInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaySample {

	private String productId;

	private String productName;

	private long amount;

	private String paymentType;

	private CardInfo cardInfo;
	private BankInfo bankInfo;
	private MobileInfo mobileInfo;
}
