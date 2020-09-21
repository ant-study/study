package kr.co.ant.study.student.imsoyeon.d_oop.payment;

import kr.co.ant.study.student.imsoyeon.d_oop.domain.RequestPayInfo;
import kr.co.ant.study.student.imsoyeon.d_oop.validate.PGValidatorY;
import kr.co.ant.study.student.imsoyeon.f_functional.second.BIPredicateValidatorY;

public class MobilePayment extends AbstractPayment  {	// 

	
	public MobilePayment(RequestPayInfo inputVO, PGValidatorY validator) {
		super(inputVO, validator);
		// TODO Auto-generated constructor stub
	}

	public MobilePayment(RequestPayInfo inputVO, BIPredicateValidatorY bpValidator) {
		super(inputVO, bpValidator);
		// TODO Auto-generated constructor stub
	}

	private PGValidatorY validator;

	@Override
	public void validate() throws Exception {
		// TODO Auto-generated method stub
		
	}	
}
