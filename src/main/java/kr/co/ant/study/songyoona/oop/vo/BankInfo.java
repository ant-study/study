/**
 * Author : yooS
 * Create Date : 2020. 9. 13.
 */
package kr.co.ant.study.songyoona.oop.vo;

import kr.co.ant.study.songyoona.oop.strategy.MoreLengthStrategy;
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
public class BankInfo extends Validating{

    //  계좌번호:ACCOUNT_NO, 은행코드:BANK_CODE, 통장비번:ACCOUNT_PW
    //  적요(보낸사람명)
    private String accountNo;       // 계좌번호

    private String bankCode;        // 은행코드

    private String accountPw;       // 계좌비밀번호

    private String remark;          // 보낸사람(받는사람표기내용)


    public void setAccountNo(String accountNo) throws Exception {

        Validating v = new BankInfo();

        // 자리수 이상(up) 체크 전략
        v.setValidate(new MoreLengthStrategy());
        v.validate(accountNo, 15);
        this.accountNo = accountNo;

    }


}
