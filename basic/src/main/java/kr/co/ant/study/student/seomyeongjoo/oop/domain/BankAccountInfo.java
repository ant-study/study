package kr.co.ant.study.student.seomyeongjoo.oop.domain;

import kr.co.ant.study.student.seomyeongjoo.oop.util.Validate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BankAccountInfo extends Validate {
    private String accountNo;
    private String bankCode;
    private String accountPw;

    public boolean validate(){
        if(accountNo.length() != size){
            System.out.printf("%s은 %s자리 수여야 합니다.\n", name, size);
            return false;
        }
        System.out.printf("%s 자리 수 확인 성공!\n", name);
        return true;
    }

}
