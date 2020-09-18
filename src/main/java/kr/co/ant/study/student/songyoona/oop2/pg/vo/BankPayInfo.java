/**
 * Author : yooS
 * Create Date : 2020. 9. 13.
 */
package kr.co.ant.study.student.songyoona.oop2.pg.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description : 계좌이체
 * @author : yooS
 * @createDate : 2020. 9. 13.
 */
@Getter
@Setter
@ToString
public class BankPayInfo implements ANTPayInfo{

    //  계좌번호:ACCOUNT_NO, 은행코드:BANK_CODE, 통장비번:ACCOUNT_PW
    //  적요(보낸사람명)
    private String accountNo;       // 계좌번호
    private String bankCode;        // 은행코드
    private String accountPw;       // 계좌비밀번호




}
