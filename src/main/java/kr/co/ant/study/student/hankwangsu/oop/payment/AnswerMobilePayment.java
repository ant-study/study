package kr.co.ant.study.student.hankwangsu.oop.payment;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;

import kr.co.ant.study.oop.domain.PaymentInfoVO;
import kr.co.ant.study.oop.service.ValidateException;
import kr.co.ant.study.student.hankwangsu.oop.pg.vo.ANTPaymentInfo;
import kr.co.ant.study.student.hankwangsu.oop.pg.vo.CardInfo;
import kr.co.ant.study.student.hankwangsu.oop.pg.vo.MobileInfo;
import kr.co.ant.study.student.hankwangsu.oop.validate.ANTValidator;

public class AnswerMobilePayment extends AbstracPayment{
	
	public AnswerMobilePayment(PaymentInfoVO vo, ANTValidator validator) {
		super(vo, validator);
	}
	
	@Override
	public void validate()throws ValidateException {
		String mobileNo = data.getMobilePayInfo().getMobileNo();
		validator.validate(mobileNo.length(), 16);
	}

	@Override
	public ANTPaymentInfo convertToANTPaymentInfo() throws Exception {
		ANTPaymentInfo paymentInfo = super.convertToANTPaymentInfo();
		
		MobileInfo mobileInfo = new MobileInfo();
		PropertyUtils.copyProperties(mobileInfo, data.getMobilePayInfo());
		
		paymentInfo.setPayInfo(mobileInfo);
		return paymentInfo;
	}

}
