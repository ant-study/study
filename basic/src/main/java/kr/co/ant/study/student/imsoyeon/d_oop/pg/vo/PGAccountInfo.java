package kr.co.ant.study.student.imsoyeon.d_oop.pg.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
/**
 * PG사에 넘겨줄 계좌 결제수단 데이터 위한 vo
 * */
public class PGAccountInfo implements PGPaymentDetailsY {

	private String accountNo;
	private String bankCode;
	private String accountPw;
}
