package kr.co.ant.study.imsoyeon.d_oop.pg.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PGPaymentInfo {

	private String productId;
	private String productName;
	private String amount;
	
	private PGCardInfo cardInfo;
	private PGAccountInfo accountInfo;
	private PGMobileInfo mobileInfo;
}
