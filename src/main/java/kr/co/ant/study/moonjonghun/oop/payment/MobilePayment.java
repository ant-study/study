package kr.co.ant.study.moonjonghun.oop.payment;

import org.apache.commons.beanutils.PropertyUtils;

import kr.co.ant.study.moonjonghun.oop.domain.MoonPaymentVO;
import kr.co.ant.study.moonjonghun.oop.exception.ValidateException;
import kr.co.ant.study.moonjonghun.oop.pg.MobileInfo;
import kr.co.ant.study.moonjonghun.oop.pg.MoonPaymentInfo;
import kr.co.ant.study.moonjonghun.oop.validation.AbstractPayment;
import kr.co.ant.study.moonjonghun.oop.validation.Validation;

public class MobilePayment extends AbstractPayment{

	public MobilePayment(MoonPaymentVO vo, Validation v) {
		super(vo, v);
	}
	
	@Override
	public MoonPaymentInfo convertPayToPG() throws Exception {
		MoonPaymentInfo paymentInfo = new MoonPaymentInfo();
		PropertyUtils.copyProperties(paymentInfo, paymentVO);
		
		MobileInfo mobileInfo = new MobileInfo();
		paymentInfo.setPayInfo(mobileInfo);
		
		return paymentInfo;
	}
	
	@Override
	public void validate() throws ValidateException {
		if(!valid.validate(paymentVO.getMobilePayInfo().getMobileNo(), 10)) throw new ValidateException("전화번호는 10자 이상이어야합니다.");
	}

}
