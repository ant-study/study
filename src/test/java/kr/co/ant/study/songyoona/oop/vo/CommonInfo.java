/**
 * Author : yooS
 * Create Date : 2020. 9. 13.
 */
package kr.co.ant.study.songyoona.oop.vo;

import kr.co.ant.study.seomyeongjoo.oop.domain.PaySubModule;
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
public class CommonInfo {
    private String productId;
    private String productName;
    private double amount;
    private String paymentType;
    private String tranDate;

    private PaySubModule paySubModule;
}
