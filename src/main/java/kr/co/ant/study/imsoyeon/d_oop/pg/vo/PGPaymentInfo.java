package kr.co.ant.study.imsoyeon.d_oop.pg.vo;

import org.apache.commons.beanutils.PropertyUtils;

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
	
	private PGPaymentDetailsY paymentDetails;
	
}
