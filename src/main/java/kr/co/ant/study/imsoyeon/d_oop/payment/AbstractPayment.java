package kr.co.ant.study.imsoyeon.d_oop.payment;

import org.apache.commons.beanutils.PropertyUtils;

import kr.co.ant.study.imsoyeon.d_oop.domain.RequestPayInfo;
import kr.co.ant.study.imsoyeon.d_oop.pg.vo.PGPaymentInfo;
import kr.co.ant.study.imsoyeon.d_oop.validate.PGValidator;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class AbstractPayment implements Payment {
	
	private RequestPayInfo inputVO;
	private PGValidator validator;	

	public AbstractPayment(RequestPayInfo inputVO, PGValidator validator) {
		super();
		this.inputVO = inputVO;
		this.validator = validator;
	}

	@Override
	public void beforeAPI() throws Exception {
				
	}
	
	@Override
	public PGPaymentInfo convertToPaymentVO() throws Exception {
		PGPaymentInfo paymentVO = new PGPaymentInfo();
		PropertyUtils.copyProperties(paymentVO, inputVO);
		return paymentVO;
	}

	@Override
	public Object requestPGAPI() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
