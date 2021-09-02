package kr.co.ant.study.student.vvooss.tran.vo;

import kr.co.ant.study.student.vvooss.tran.common.PaymentType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 공통부분
 * @author dev
 *
 */
@Getter
@Setter
@ToString
public class  PGApiVO <T extends SettleInfo> {

	private String product_id;
	private String product_name;
	private long   amount;
	private PaymentType paymentType;
	private T settleInfo;
	
	public void setSettleInfo(T t) {
		this.settleInfo = t;
	}
	public T getSettleInfo() {
		return settleInfo;
	}
}
