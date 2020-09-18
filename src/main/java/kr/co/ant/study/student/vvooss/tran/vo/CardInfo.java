package kr.co.ant.study.student.vvooss.tran.vo;

import kr.co.ant.study.student.vvooss.tran.common.CardCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CardInfo extends SettleInfo {

	private CardCode cardCode;
	private String 		cardNo;
	private String 		expireDate;

	public CardInfo() {
		super.setFieldName(this.getClass().getName());
	}
	
}
