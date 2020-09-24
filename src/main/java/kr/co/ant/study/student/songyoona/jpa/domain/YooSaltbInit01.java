/**
 * Author : yooS
 * Create Date : 2020. 9. 23.
 */
package kr.co.ant.study.student.songyoona.jpa.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
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
@DynamicUpdate  //  (update 시 null 인필드 제외) value가 변경된 컬럼만 update쳐라
@Getter @Setter //   테이블 안에 부모자식 있을경우 쓰는듯 @Inheritance(strategy = InheritanceType.JOINED)@DiscriminatorColumn(name = "child")
public class YooSaltbInit01 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 19, columnDefinition="BIGINT unsigned")
    private Long initId;

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

    @Column(name = "stock_amt", nullable=false, columnDefinition="decimal(25,5) default '0.00000'")
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

    @OneToMany(mappedBy = "yooSaltbInit01" , cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REMOVE}) // , cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REMOVE}
    private List<YooSaltbInit01Hst> yooInitHistories;

    // 자식 entity를 담아둠.
    public void addYooInitHst(YooSaltbInit01Hst hist) {
        if(yooInitHistories == null) {
            yooInitHistories = new ArrayList<YooSaltbInit01Hst>();
        }

        hist.setYooSaltbInit01(this);
        yooInitHistories.add(hist);
    }

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

    /**
     * insert 되기전 (persist 되기전) 실행된다. ex)default 설정할때 유용
     * */
    @PrePersist
    public void prePersist() {
        this.sysRegId = this.sysRegId == null ? "Yoo" : this.sysRegId;
    }

    /**
     * 엔티티 관리자 지속 작업이 실제로 실행되거나 계단식으로 실행 된 후에 실행됩니다.
     * 이 호출은 데이터베이스 INSERT가 실행 된 후에 호출됩니다.
     */
    @PostPersist
    public void initHst() {

    }
}
