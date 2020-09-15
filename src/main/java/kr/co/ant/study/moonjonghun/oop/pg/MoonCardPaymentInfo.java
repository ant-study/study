package kr.co.ant.study.moonjonghun.oop.pg;

import kr.co.ant.study.moonjonghun.oop.domain.MoonCardInfoVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoonCardPaymentInfo {

	private String productId;
	private String productName;
	private int amount;
	
	private MoonCardInfoVO cardInfo;
}
