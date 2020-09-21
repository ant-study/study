package kr.co.ant.study.student.imsoyeon.d_oop.payment;

import kr.co.ant.study.student.imsoyeon.d_oop.domain.RequestPayInfo;
import kr.co.ant.study.student.imsoyeon.d_oop.validate.PGValidatorY;
import kr.co.ant.study.student.imsoyeon.f_functional.second.BIPredicateValidatorY;

public class AccountPayment extends AbstractPayment {
	
//	1.d_oop Test
	public AccountPayment(RequestPayInfo inputVO, PGValidatorY validator) {
		super(inputVO, validator);
		// TODO Auto-generated constructor stub
	}

//	2.f_functional Test
	public AccountPayment(RequestPayInfo inputVO, BIPredicateValidatorY bpValidator) {
		super(inputVO, bpValidator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void validate() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
