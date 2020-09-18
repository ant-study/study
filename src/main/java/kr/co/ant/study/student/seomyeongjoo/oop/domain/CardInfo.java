package kr.co.ant.study.student.seomyeongjoo.oop.domain;

import kr.co.ant.study.student.seomyeongjoo.oop.util.Validate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CardInfo extends Validate {
    private String cardNo;
    private String cardCode;
    private String expireDate;

    public boolean validate(){
        if(cardNo.length() != size){
            System.out.printf("%s은 %s자리 수여야 합니다.\n", name, size);
            return false;
        }
        System.out.printf("%s 자리 수 확인 성공!\n", name);
        return true;
    }
}
