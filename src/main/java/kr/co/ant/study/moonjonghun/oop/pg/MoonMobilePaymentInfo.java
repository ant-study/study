package kr.co.ant.study.moonjonghun.oop.pg;

import kr.co.ant.study.moonjonghun.oop.domain.MoonMobileInfoVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoonMobilePaymentInfo {
	
	private String productId;
	private String productName;
	private int amount;
	
	private MoonMobileInfoVO mobileInfo;
	
}
