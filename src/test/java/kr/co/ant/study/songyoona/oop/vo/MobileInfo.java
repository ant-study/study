/**
 * Author : yooS
 * Create Date : 2020. 9. 13.
 */
package kr.co.ant.study.songyoona.oop.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description : 휴대폰결제
 * @author : yooS
 * @createDate : 2020. 9. 13.
 */
@Getter
@Setter
@ToString
public class MobileInfo extends CommonInfo{
    // 핸드폰번호:mobile_no, 명의자:user_name, 생년월일:biRth_day
    private String mobileNo;        // 핸드폰번호
    private String userName;        // 명의자
    private String birthDay;        // 생년월일
    private String mobileCom;       // 통신사
}
