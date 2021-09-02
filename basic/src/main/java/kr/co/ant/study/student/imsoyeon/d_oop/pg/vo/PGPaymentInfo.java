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
	
	/*
	 * JSON serialize / deserialize 시 
	 * response 객체에 특정 필드틑 추가하고 싶지 않은 경우 
	 * @JsonIgnore로 미포함시킬 수 있음
	 * */
	@JsonIgnore
	private PGPaymentDetailsY paymentDetails;
	
}
