/**
 * Author : yooS
 * Create Date : 2020. 9. 23.
 */
package kr.co.ant.study.student.songyoona.jpa.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

/**
 * @description :
 * Increment이면 ==> @GeneratedValue(strategy = GenerationType.IDENTITY)
 * Sequence면 ==> @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "시퀀스명")
 * @author : yooS
 * @createDate : 2020. 9. 23.
 */
@Entity
@Table(name="yoo_saltb_init01")
@DynamicUpdate  //  value가 변경된 컬럼만 update쳐라
@Getter
@Setter
public class YooSaltbInit01 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID initId;    // private long id;
    private String tenantId;
    private String enplcCd;
    private String storeCd;
    private String itemCd;
    //@Column(columnDefinition="default 'N'", scale='5', precision='5') // scale:소수점 자릿수, precision: 소숫점을 포함한 전체 자리수
    private String stockQty;
    private String stockAmt;
    private String sysRegId;
    private LocalDateTime sysRegDate;
    private String sysUpdId;
    private LocalDateTime sysUpdDate;
}
