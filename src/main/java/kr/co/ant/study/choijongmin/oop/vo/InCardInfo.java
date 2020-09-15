/**
 * 
 */
package kr.co.ant.study.choijongmin.oop.vo;

import kr.co.ant.study.choijongmin.oop.validate.PaymentStrategy;
import kr.co.ant.study.oop.service.ValidateException;
import lombok.Getter;
import lombok.Setter;

/**
 * @author crono
 *
 */
@Getter
@Setter
public abstract class InCardInfo implements PaymentStrategy {
	
	private String cardNo;
	private String cardCode;
	private String expireDate;
	
	@Override
	public void validate() throws ValidateException {
		if ( this.getCardNo().length() != 16 ) {
			throw new ValidateException("16자리~");
		}
	}
	
}
