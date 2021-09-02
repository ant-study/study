package kr.co.ant.study.student.moonjonghun.oop.payment;

import org.apache.commons.beanutils.PropertyUtils;

import kr.co.ant.study.student.moonjonghun.oop.domain.MoonPaymentVO;
import kr.co.ant.study.student.moonjonghun.oop.exception.ValidateException;
import kr.co.ant.study.student.moonjonghun.oop.pg.CardInfo;
import kr.co.ant.study.student.moonjonghun.oop.pg.MoonPaymentInfo;
import kr.co.ant.study.student.moonjonghun.oop.validation.AbstractPayment;
import kr.co.ant.study.student.moonjonghun.oop.validation.Validation;

public class CardPayment extends AbstractPayment{
	
	public CardPayment(MoonPaymentVO vo, Validation v) {
		super(vo, v);
	}
	
	@Override
	public MoonPaymentInfo convertPayToPG() throws Exception {
		MoonPaymentInfo paymentInfo = new MoonPaymentInfo();
		PropertyUtils.copyProperties(paymentInfo, paymentVO);
		
		CardInfo cardInfo = new CardInfo();
		PropertyUtils.copyProperties(cardInfo, paymentVO.getCardPayInfo());
		paymentInfo.setPayInfo(cardInfo);
		
		return paymentInfo;
	}
	
	@Override
	public void validate() throws ValidateException{
		if(!valid.validate(paymentVO.getCardPayInfo().getCardNo(), 16)) throw new ValidateException("카드번호가 정확하지 않습니다.");
	}

}
