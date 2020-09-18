package kr.co.ant.study.student.imsoyeon.d_oop.pg.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	private PGPaymentDetailsY paymentDetails;
	
}
