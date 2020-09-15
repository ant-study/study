package kr.co.ant.study.hankwangsu.oop.payment;

import org.apache.commons.beanutils.PropertyUtils;

import kr.co.ant.study.hankwangsu.oop.pg.vo.ANTPaymentInfo;
import kr.co.ant.study.hankwangsu.oop.validate.ANTValidator;
import kr.co.ant.study.oop.domain.PaymentInfoVO;

public abstract class AbstracPayment implements Payment{
	
	protected ANTValidator validator;
	protected PaymentInfoVO data;

	public AbstracPayment(PaymentInfoVO vo, ANTValidator validator) {
		this.data = vo;
		this.validator = validator;
	}
	
	public ANTPaymentInfo convertToANTPaymentInfo() throws Exception {
		ANTPaymentInfo antPaymentInfo = new ANTPaymentInfo();
		PropertyUtils.copyProperties(antPaymentInfo, data);
		return antPaymentInfo;
	}
	
	@Override
	public void logging() {
		System.out.println("ssss");
	}
	
	

}
