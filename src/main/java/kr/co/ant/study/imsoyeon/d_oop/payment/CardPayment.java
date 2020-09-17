package kr.co.ant.study.imsoyeon.d_oop.payment;

import org.apache.commons.beanutils.PropertyUtils;

import kr.co.ant.study.imsoyeon.d_oop.domain.RequestPayInfo;
import kr.co.ant.study.imsoyeon.d_oop.pg.vo.PGCardInfo;
import kr.co.ant.study.imsoyeon.d_oop.pg.vo.PGPaymentInfo;
import kr.co.ant.study.imsoyeon.d_oop.validate.PGValidatorY;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CardPayment extends AbstractPayment {
	
	
	public CardPayment(RequestPayInfo inputVO, PGValidatorY validator) {
		/*
		부모 클래스 field값 세팅해주고 싶지?
				 
		super.setInputVO(inputVO);
		super.setValidator(validator);
		 * */
		super(inputVO, validator);
	}	

	@Override
	public void validate() throws Exception {
		String cardNo = super.getInputVO().getCardInfo().getCardNo();
		super.getValidator().validate(cardNo, 16);
	}

	@Override
	public PGPaymentInfo convertToPaymentVO() throws Exception {
//		common data
		PGPaymentInfo payment = super.convertToPaymentVO();
//		card data
		PGCardInfo card = new PGCardInfo();
		PropertyUtils.copyProperties(card, super.getInputVO().getCardInfo());
		
		payment.setPaymentDetails(card);
		return payment;
	}
	
}
