package kr.co.ant.study.seomyeongjoo.oop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaySample {

	private String productId;

	private String productName;

	private long amount;

	private String paymentType;

	private PaySubModule paySubModule;

}
