package kr.co.ant.study.student.imsoyeon.d_oop.payment;

import org.apache.commons.beanutils.PropertyUtils;

import kr.co.ant.study.student.imsoyeon.d_oop.domain.RequestPayInfo;
import kr.co.ant.study.student.imsoyeon.d_oop.pg.vo.PGMobileInfo;
import kr.co.ant.study.student.imsoyeon.d_oop.pg.vo.PGPaymentInfo;
import kr.co.ant.study.student.imsoyeon.d_oop.validate.PGValidatorY;
import kr.co.ant.study.student.imsoyeon.f_functional.second.BIPredicateValidatorY;

public class MobilePayment extends AbstractPayment  {	// 

//	1.d_oop Test
	public MobilePayment(RequestPayInfo inputVO, PGValidatorY validator) {
		super(inputVO, validator);
	}

//	2.f_functional Test
	public MobilePayment(RequestPayInfo inputVO, BIPredicateValidatorY bpValidator) {
		super(inputVO, bpValidator);
	}

	@Override
	public void validate() throws Exception {
//		validation 체크 대상
		String mobileNo = super.getInputVO().getMobileInfo().getMobileNo();
		
		super.getBpValidator().validate(mobileNo, 11);
		
	}

	@Override
	public PGPaymentInfo convertToPaymentVO() throws Exception {
//		common data
		PGPaymentInfo payment = super.convertToPaymentVO();	
//		mobile data
		PGMobileInfo mobile = new PGMobileInfo();
		PropertyUtils.copyProperties(mobile, super.getInputVO().getMobileInfo());
		
		payment.setPaymentDetails(mobile);
		return payment;
	}
	
	
}
