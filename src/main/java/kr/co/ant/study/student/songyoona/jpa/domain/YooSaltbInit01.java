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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name="yoo_saltb_init01", uniqueConstraints={@UniqueConstraint(columnNames={"tenant_id","enplc_cd","store_cd","item_cd"})}) //
@DynamicUpdate  //  value가 변경된 컬럼만 update쳐라
@Getter @Setter
public class YooSaltbInit01 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 19, columnDefinition="BIGINT unsigned")
    private Long initId;    // private long id;

    @Column(name = "tenant_id", nullable=false, length=27)
    private String tenantId;

    @Column(name = "enplc_cd", nullable=false, length=4)
    private String enplcCd;

    @Column(name = "store_cd", nullable=false, length=2)
    private String storeCd;

    @Column(name = "item_cd", nullable=false, length=30)
    private String itemCd;

    @Column(name = "stock_qty", nullable=false, columnDefinition="decimal(25,5) default '0.00000'") // scale:소수점 자릿수, precision: 소숫점을 포함한 전체 자리수
    private long stockQty;

    @Column(name = "stock_amt", nullable=false, columnDefinition="decimal(25,5) default '0.00000' ")
    private long stockAmt;

    @Column(name = "sys_reg_id", nullable=false, length=27)
    private String sysRegId;

    @DateTimeFormat
    @CreationTimestamp
    @Column(name = "sys_reg_date", nullable=false)//, columnDefinition="default CURRENT_TIMESTAMP"
    private LocalDateTime sysRegDate;

    @Column(name = "sys_upd_id", length=27)
    private String sysUpdId;

    @Column(name = "sys_upd_date")
    private LocalDateTime sysUpdDate;

    /**
     *
     * @createDate : 2020. 9. 24.
     * @param initId2
     * @return
     * @modifiedHistory :
     */
//    public YooSaltbInit01 setInitId(UUID initId2) {
//        YooSaltbInit01 e = new YooSaltbInit01();
//        e.setInitId(this.initId);
//        return e;
//    }


}
