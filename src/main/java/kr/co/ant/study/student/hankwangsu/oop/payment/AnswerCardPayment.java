package kr.co.ant.study.hankwangsu.oop.payment;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;

import kr.co.ant.study.hankwangsu.oop.pg.vo.ANTPaymentInfo;
import kr.co.ant.study.hankwangsu.oop.pg.vo.CardInfo;
import kr.co.ant.study.hankwangsu.oop.validate.ANTValidator;
import kr.co.ant.study.oop.domain.PaymentInfoVO;
import kr.co.ant.study.oop.service.ValidateException;

public class AnswerCardPayment extends AbstracPayment{
	
	public AnswerCardPayment(PaymentInfoVO vo, ANTValidator validator) {
		super(vo, validator);
	}
	
	@Override
	public void validate()throws ValidateException {
		String cardNo = data.getCardPayInfo().getCardNo();
		validator.validate(cardNo.length(), 16);
	}

	@Override
	public ANTPaymentInfo convertToANTPaymentInfo() throws Exception {
		ANTPaymentInfo paymentInfo = super.convertToANTPaymentInfo();
		
		CardInfo cardInfo = new CardInfo();
		PropertyUtils.copyProperties(cardInfo, data.getCardPayInfo());
		
		paymentInfo.setPayInfo(cardInfo);
		return paymentInfo;
	}

}
