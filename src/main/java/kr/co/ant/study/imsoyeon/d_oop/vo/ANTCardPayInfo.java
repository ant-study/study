package kr.co.ant.study.imsoyeon.d_oop.vo;

import org.apache.commons.beanutils.PropertyUtils;

import kr.co.ant.study.imsoyeon.d_oop.domain.CardInfoVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ANTCardPayInfo implements Payment {

	private String productId;
	private String productName;
	private String amount;
	
	private String cardNo;
	private String cardCode;
	private String expireDate;
	
	private CardInfoVO cardInfo;

	@Override
	public Object convertVO(RequestPayInfo request) throws Exception {
		
		return null;
	}
}
