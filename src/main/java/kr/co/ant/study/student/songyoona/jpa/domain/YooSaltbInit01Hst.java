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
 * @author : yooS
 * @createDate : 2020. 9. 23.
 */
@Entity
@Table(name="yoo_saltb_init01_hst")
@DynamicUpdate  //  value가 변경된 컬럼만 update쳐라
@Getter
@Setter
public class YooSaltbInit01Hst {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID initHstId;
    private long initId;
    private int seq;
    private String EnentDscd;
    private String tenantId;
    private String enplcCd;
    private String storeCd;
    private String itemCd;
    private String stockQty;
    private String stockAmt;
    private String sysRegId;
    private LocalDateTime sysRegDate;
    private String sysUpdId;
    private LocalDateTime sysUpdDate;
}
