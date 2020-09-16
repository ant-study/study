package kr.co.ant.study.songyoona.oop2.domain;

import kr.co.ant.study.songyoona.oop2.pg.vo.BankPayInfo;
import kr.co.ant.study.songyoona.oop2.pg.vo.CardPayInfo;
import kr.co.ant.study.songyoona.oop2.pg.vo.MobilePayInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentInfo {
	private String productId;
	private String productName;
	private int amount;
	private String paymentType;

	private CardPayInfo cardPayInfo;
	private MobilePayInfo mobilePayInfo;
	private BankPayInfo bankPayInfo;

}
