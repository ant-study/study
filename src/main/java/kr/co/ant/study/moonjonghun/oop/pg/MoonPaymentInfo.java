package kr.co.ant.study.moonjonghun.oop.pg;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoonPaymentInfo {

	private String productId;
	private String productName;
	private int amount;
	private String paymentType;
	
	@JsonIgnore
	private MoonPayInfo payInfo;
}
