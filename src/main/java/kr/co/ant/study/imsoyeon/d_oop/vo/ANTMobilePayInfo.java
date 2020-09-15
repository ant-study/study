package kr.co.ant.study.imsoyeon.d_oop.vo;

import kr.co.ant.study.imsoyeon.d_oop.domain.CardInfoVO;
import kr.co.ant.study.imsoyeon.d_oop.domain.MobileInfoVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ANTMobilePayInfo {
	
	private String productId;
	private String productName;
	private String amount;	

	private String mobileNo;
	private String userName;
	private String birthday;
	
	private MobileInfoVO mobileInfo;
}
