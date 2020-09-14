/**
 * Author : yooS
 * Create Date : 2020. 9. 13.
 */
package kr.co.ant.study.songyoona.oop.vo;

import kr.co.ant.study.songyoona.oop.strategy.FixLengthStrategy;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 13.
 */
@Getter
@Setter
@ToString
public class CardInfo extends Validating{
    // 카드번호:card_no, 카드사:card_code, 유효일자:expire_date
    // 카드명의자, 비밀번호,
    private String cardNo;          // 카드번호

    private String cardCode;        // 카드사

    private String expireDate;      // 유효기간

    private String cardOwner;       // 카드명의자

    private String cardPw;      // 카드비밀번호

    public void setCardNo(String cardNo) throws Exception {

        Validating v = new CardInfo();

        v.setValidate(new FixLengthStrategy());
        v.validate(this.cardNo, 16);
        this.cardNo = cardNo;

    }
}
