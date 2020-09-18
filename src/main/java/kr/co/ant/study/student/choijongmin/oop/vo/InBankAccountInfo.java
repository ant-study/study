package kr.co.ant.study.student.choijongmin.oop.vo;

import kr.co.ant.study.student.choijongmin.oop.service.PaymentAbs;
import kr.co.ant.study.oop.service.ValidateException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InBankAccountInfo extends PaymentAbs {

	private String accountNo;
	private String bankCode;
	private String accountPw;
	
	@Override
	public void validate() throws ValidateException {
		if ( this.getAccountNo().length() != 20 ) {
			throw new ValidateException("20자리~");
		}
	}
	
}
