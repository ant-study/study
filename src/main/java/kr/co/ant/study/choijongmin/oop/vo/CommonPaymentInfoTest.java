/**
 * Author : jmChoi
 * Create Date : 2020. 9. 16.
 */
package kr.co.ant.study.choijongmin.oop.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @description : 
 * @author : jmChoi
 * @createDate : 2020. 9. 16.
 */
@Getter
@Setter
public class CommonPaymentInfoTest {
	private String productId;
	private String productName;
	private Long amount;
	private String paymentType;
}
