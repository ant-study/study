package kr.co.ant.study.songyoona.oop;

import kr.co.ant.study.oop.domain.PaySubModule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaySample {

	private String productId;

	private String productName;

	private long amount;

	private String paymentType;

	private PaySubModule paySubModule;

}
