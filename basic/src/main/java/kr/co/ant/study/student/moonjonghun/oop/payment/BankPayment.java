package kr.co.ant.study.student.moonjonghun.oop.payment;

import org.apache.commons.beanutils.PropertyUtils;

import kr.co.ant.study.student.moonjonghun.oop.domain.MoonPaymentVO;
import kr.co.ant.study.student.moonjonghun.oop.exception.ValidateException;
import kr.co.ant.study.student.moonjonghun.oop.pg.BankAccountInfo;
import kr.co.ant.study.student.moonjonghun.oop.pg.MoonPaymentInfo;
import kr.co.ant.study.student.moonjonghun.oop.validation.AbstractPayment;
import kr.co.ant.study.student.moonjonghun.oop.validation.FixedLengthValidation;
import kr.co.ant.study.student.moonjonghun.oop.validation.Validation;

public class BankPayment extends AbstractPayment{
	
	public BankPayment(MoonPaymentVO vo, Validation v) {
		super(vo, v);
	}
	
	@Override
	public MoonPaymentInfo convertPayToPG() throws Exception {
		MoonPaymentInfo bankPaymentInfo = new MoonPaymentInfo();
		PropertyUtils.copyProperties(bankPaymentInfo, paymentVO);
		
		BankAccountInfo bankInfo = new BankAccountInfo();
		PropertyUtils.copyProperties(bankInfo, paymentVO.getAccountPayInfo());
		bankPaymentInfo.setPayInfo(bankInfo);
		
		return bankPaymentInfo;
	}
	
	@Override
	public void validate() throws ValidateException {
		if(!valid.validate(paymentVO.getAccountPayInfo().getAccountNo(), 20)) throw new ValidateException("계좌정보가 정확하지 않습니다.");
	}



}
