package kr.co.ant.study.moonjonghun.oop.pg;

import kr.co.ant.study.moonjonghun.oop.domain.MoonAccountInfoVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoonBankPaymentInfo {
	
	private String productId;
	private String productName;
	private int amount;
	
	private MoonAccountInfoVO accountInfo;
}
