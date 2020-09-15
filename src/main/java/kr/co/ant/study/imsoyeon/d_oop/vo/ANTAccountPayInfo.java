package kr.co.ant.study.imsoyeon.d_oop.vo;

import kr.co.ant.study.imsoyeon.d_oop.domain.AccountInfoVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ANTAccountPayInfo {
	
	private String productId;
	private String productName;
	private String amount;
	
	private String accountNo;
	private String bankCode;
	private String accountPw;
	
	private AccountInfoVO accountInfoVo;
}
