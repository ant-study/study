/**
 * Author : yooS
 * Create Date : 2020. 9. 23.
 */
package kr.co.ant.study.student.songyoona.jpa.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 23.
 */
@Entity
@Table(name="yoo_saltb_init01_hst", uniqueConstraints={@UniqueConstraint(name="init_id", columnNames={"INIT_ID","seq","EVENT_DSCD"})})//
@DynamicUpdate  //  value가 변경된 컬럼만 update쳐라
@DiscriminatorValue("hst")
@Getter @Setter @Slf4j
public class YooSaltbInit01Hst{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(nullable = false, length = 25, columnDefinition="BIGINT unsigned")
    private Long initHstId;

//    @Column(name = "INIT_ID", nullable = false, length = 19, columnDefinition="BIGINT unsigned")
//    private Long initId;
    // 자식이니까 many이고 부모는 one
    @ManyToOne @JoinColumn(name = "init_id", nullable=false)
    private YooSaltbInit01 initId;
    public void setYooSaltbInit01(YooSaltbInit01 initId) {
        this.initId = initId;
        // 무한루프에 빠지지 않도록 체크.
        if(!initId.getYooInitHistories().contains(this)) {
            initId.getYooInitHistories().add(this);
        }
    }

    @Column(nullable = false, length = 19, columnDefinition="int unsigned")
    private int seq;

    @Column(name = "EVENT_DSCD", nullable=false, length=1)
    private String EventDscd;

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
    @Column(name = "sys_reg_date", nullable=false)  //@CreationTimestamp
    private LocalDateTime sysRegDate;

    @Column(name = "sys_upd_id", length=27)
    private String sysUpdId;

    @Column(name = "sys_upd_date")
    private LocalDateTime sysUpdDate;

    @PreUpdate
    public void preUpdate() {
        log.info("Init01Hst PreUpdate 한다!!!");
        this.EventDscd = "D";
        this.sysUpdId = "UpYoo";
    }

}
