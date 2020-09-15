package kr.co.ant.study.hankwangsu.oop.pg.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ANTPaymentInfo {
	private String productId;
	private String productName;
	private int amount;
	private String paymentType;
	
    @JsonIgnore
	private ANTPayInfo payInfo;
}
