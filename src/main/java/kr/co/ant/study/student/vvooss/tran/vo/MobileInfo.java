package kr.co.ant.study.student.vvooss.tran.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MobileInfo extends SettleInfo{

	private String mobileNo;
	private String userName;
	private String birthday;

	public MobileInfo() {
		System.out.println(this.getClass().getName() + " contructor called");
		super.setFieldName(this.getClass().getName());
	}
	
}
