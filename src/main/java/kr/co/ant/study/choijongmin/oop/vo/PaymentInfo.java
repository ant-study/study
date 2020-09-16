package kr.co.ant.study.choijongmin.oop.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentInfo extends CommonPaymentInfoTest{
	public InCardInfo inCardInfo;
	public InBankAccountInfo inBankAccountInfo;
	public InMobileInfo inMobileInfo;
}