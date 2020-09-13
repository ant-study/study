package kr.co.ant.study.vvooss.tran.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BankAccountInfo extends SettleInfo {

	private String accountNo;
	private String bankCode;
	private String accountPw;
	
	public BankAccountInfo() {
		super.setFieldName(this.getClass().getName());
	}
}
