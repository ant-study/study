package kr.co.ant.study.student.imsoyeon.d_oop.payment;

import org.apache.commons.beanutils.PropertyUtils;

import kr.co.ant.study.student.imsoyeon.d_oop.domain.RequestPayInfo;
import kr.co.ant.study.student.imsoyeon.d_oop.pg.vo.PGAccountInfo;
import kr.co.ant.study.student.imsoyeon.d_oop.pg.vo.PGPaymentInfo;
import kr.co.ant.study.student.imsoyeon.d_oop.validate.PGValidatorY;
import kr.co.ant.study.student.imsoyeon.f_functional.second.BIPredicateValidatorY;

public class AccountPayment extends AbstractPayment {
	
//	1.d_oop Test
	public AccountPayment(RequestPayInfo inputVO, PGValidatorY validator) {
		super(inputVO, validator);
	}

//	2.f_functional Test
	public AccountPayment(RequestPayInfo inputVO, BIPredicateValidatorY bpValidator) {
		super(inputVO, bpValidator);
	}

	@Override
	public void validate() throws Exception {
//		validation 체크 대상
		String accountNo = super.getInputVO().getAccountInfo().getAccountNo();
		
		super.getBpValidator().validate(accountNo, 15);
		
	}

	@Override
	public PGPaymentInfo convertToPaymentVO() throws Exception {
//		common data
		PGPaymentInfo payment = super.convertToPaymentVO();		
//		card data
		PGAccountInfo account = new PGAccountInfo();
		PropertyUtils.copyProperties(account, super.getInputVO().getAccountInfo());
		
		payment.setPaymentDetails(account);		
		return payment;
	}
	
	
}
