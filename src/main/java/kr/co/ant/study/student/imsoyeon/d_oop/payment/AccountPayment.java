package kr.co.ant.study.student.imsoyeon.d_oop.payment;

import kr.co.ant.study.student.imsoyeon.d_oop.domain.RequestPayInfo;
import kr.co.ant.study.student.imsoyeon.d_oop.validate.PGValidatorY;

public class AccountPayment extends AbstractPayment {	// extends AbstractPayment

	
	public AccountPayment(RequestPayInfo inputVO, PGValidatorY validator) {
		super(inputVO, validator);
		// TODO Auto-generated constructor stub
	}

	private PGValidatorY validator;

	@Override
	public void validate() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
