package kr.co.ant.study.seomyeongjoo.oop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CardInfo {
    private String cardNo;
    private String cardCode;
    private String exprireDate;
}
