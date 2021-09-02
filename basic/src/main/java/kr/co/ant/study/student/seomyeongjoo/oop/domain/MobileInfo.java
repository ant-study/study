package kr.co.ant.study.student.seomyeongjoo.oop.domain;

import kr.co.ant.study.student.seomyeongjoo.oop.util.Validate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MobileInfo extends Validate {
    private String mobileNo;
    private String userName;
    private String birthday;

    public boolean validate(){
        if(mobileNo.length() != size){
            System.out.printf("%s은 %s자리 수여야 합니다.\n", name, size);
            return false;
        }
        System.out.printf("%s 자리 수 확인 성공!\n", name);
        return true;
    }
}
