package kr.co.ant.study.imsoyeon.d_oop.vo;

import kr.co.ant.study.imsoyeon.d_oop.domain.AccountInfoVO;
import kr.co.ant.study.imsoyeon.d_oop.domain.CardInfoVO;
import kr.co.ant.study.imsoyeon.d_oop.domain.MobileInfoVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestPayInfo {

	private String type;
	
	private String productId;
	private String productName;
	private String amount;
	
	private CardInfoVO cardInfo;
	private AccountInfoVO accountInfo;
	private MobileInfoVO mobileInfo;
}
