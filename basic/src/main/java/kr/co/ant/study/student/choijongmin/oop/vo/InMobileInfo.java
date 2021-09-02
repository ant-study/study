package kr.co.ant.study.student.choijongmin.oop.vo;

import kr.co.ant.study.student.choijongmin.oop.service.PaymentAbs;
import kr.co.ant.study.oop.service.ValidateException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InMobileInfo extends PaymentAbs {

	private String mobileNo;
	private String userName;
	private String birthday;
	
	@Override
	public void validate() throws ValidateException {
		if ( this.getMobileNo().length() != 10 ) {
			throw new ValidateException("10자리~");
		}
	}
	
}
