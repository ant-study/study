package kr.co.ant.study.seomyeongjoo.oop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BankAccountInfo {
    private String accountNo;
    private String bankCode;
    private String accountPw;


    public BankAccountInfo(String accountNo, String bankCode, String accountPw) {
        this.accountNo = accountNo;
        this.bankCode = bankCode;
        this.accountPw = accountPw;
    }
}
