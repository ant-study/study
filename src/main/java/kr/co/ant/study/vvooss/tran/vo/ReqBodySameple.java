package kr.co.ant.study.vvooss.tran.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
/**
 * front에서 입력이 올떄 바디랑 매핑되는 vo
 * @author dev
 *
 */
public class ReqBodySameple {
	
	private String productId;
	private String productName;
	private int    amount;
	private String paymentType;
	private String accountNo;
	private String bankCode;
	private String accountPw;
	private String mobileNo;
	private String userName;
	private String birthday;
	private String cardNo;
	private String cardCode;
	private String expireDate;

}
