package kr.co.ant.study.songyoona.oop2.pg.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ANTPaymentInfo {
	// 결제수단 공통 파라미터
    private String productId;
	private String productName;
	private int amount;
	private String paymentType;

    @JsonIgnore
	private ANTPayInfo payInfo;
}
