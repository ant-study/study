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
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import kr.co.ant.study.jpa.jpql.domain.BaseStockHistory;
import kr.co.ant.study.student.songyoona.jpa.DataDtoListener;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @description : Increment이면 ==> @GeneratedValue(strategy = GenerationType.IDENTITY) Sequence면 ==> @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "시퀀스명")
 * @author : yooS
 * @createDate : 2020. 9. 23.
 */
//@EntityListeners(DataDtoListener.class) // data-jpa 사용시 데이터 변경시 알림을 받음.
@Entity
@Table(name = "yoo_saltb_init01", uniqueConstraints = { @UniqueConstraint(name = "ux01_init", columnNames = { "tenant_id", "enplc_cd", "store_cd", "item_cd" })
                            //,@UniqueConstraint(name = "ix01_init", columnNames = { "tenant_id", "enplc_cd", "item_cd" })
                            }) //
@DynamicUpdate // (update 시 null 인필드 제외) value가 변경된 컬럼만 update쳐라
@Getter
@Setter
@Slf4j // 테이블 안에 부모자식 있을경우 쓰는듯 @Inheritance(strategy = InheritanceType.JOINED)@DiscriminatorColumn(name = "child")
public class YooSaltbInit01 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="init_id", nullable = false, length = 19, columnDefinition = "BIGINT unsigned")
    private Long initId;

    @Column(name = "tenant_id", nullable = false, length = 27)
    private String tenantId;

    @Column(name = "enplc_cd", nullable = false, length = 4)
    private String enplcCd;

    @Column(name = "store_cd", nullable = false, length = 2)
    private String storeCd;

    @Column(name = "item_cd", nullable = false, length = 30)
    private String itemCd;

    @Column(name = "stock_qty", nullable = false, columnDefinition = "decimal(25,5) default '0.00000'") // scale:소수점 자릿수, precision: 소숫점을 포함한 전체 자리수
    private long stockQty;

    @Column(name = "stock_amt", nullable = false, columnDefinition = "decimal(25,5) default '0.00000'")
    private long stockAmt;

    @Column(name = "sys_reg_id", nullable = false, length = 27)
    private String sysRegId;

    @DateTimeFormat
    @CreationTimestamp
    @Column(name = "sys_reg_date", nullable = false) // , columnDefinition="default CURRENT_TIMESTAMP"
    private LocalDateTime sysRegDate;

    @Column(name = "sys_upd_id", length = 27)
    private String sysUpdId;

    @Column(name = "sys_upd_date")
    private LocalDateTime sysUpdDate;

    @Transient // 필드를 매핑하고 싶지 않을때 : 데이터베이스에 저장하지도 않고 조회하지도 않는다.
    private int seq;

    // cascade:영속성 전이(영속성 객체에 수행하는 행동이 자식까지 전파) / orphanRemoval = true: 부모가 삭제될 경우 자동으로 자식도 삭제
    @OneToMany(mappedBy = "initId", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REMOVE, CascadeType.MERGE }, orphanRemoval = true) // , cascade = {CascadeType.PERSIST, CascadeType.DETACH,
    private List<YooSaltbInit01Hst> yooInitHistories;

    // 자식 entity를 담아둠.
    public void addYooInitHst(YooSaltbInit01Hst hist) {
        if (yooInitHistories == null) {
            yooInitHistories = new ArrayList<YooSaltbInit01Hst>();
        }
        //yooInitHistories = new ArrayList<YooSaltbInit01Hst>();

        hist.setInitId(this);
        yooInitHistories.add(hist);


        // 두번째 방식
//        this.yooInitHistories.add(hist);
//        if(hist.getInitId() != this) {  // 무한루프에 빠지지 않도록 체크.
//            hist.setYooSaltbInit01(this);
//        }
    }

    // event에 따른..
//    public void addYooInitHst(String eventName) {
//        YooSaltbInit01Hst history = new YooSaltbInit01Hst();
//        history.setYooSaltbInit01(this, eventName);
//        yooInitHistories.add(history);
//    }

    /*
     * @PostLoad: 해당 엔티티를 새로 불러오거나 refresh 한 이후.
     *
     * @PrePersist: 해당 엔티티를 저장하기 이전
     *
     * @PostPersist: 해당 엔티티를 저장한 이후
     *
     * @PreUpdate: 해당 엔티티를 업데이트 하기 이전
     *
     * @PostUpdate: 해당 엔티티를 업데이트 한 이후
     *
     * @PreRemove: 해당 엔티티를 삭제하기 이전
     *
     * @PostRemove: 해당 엔티티를 삭제한 이후
     */

    /**
     * insert 되기전 (persist 되기전) 실행된다. ex)default 설정할때 유용
     */
    @PrePersist
    public void prePersist() {
        log.info("insert 이전  ***** prePersist 한다!!!");
        this.sysRegId = this.sysRegId == null ? "Yoo" : this.sysRegId;
    }

    /**
     * insert trigger 교체 엔티티 관리자 지속 작업이 실제로 실행되거나 계단식으로 실행 된 후에 실행됩니다. 이 호출은 데이터베이스 INSERT가 실행 된 후에 호출됩니다. manager persist 에 의해 실행되고 불립니다. SQL INSERT 이후에 대응될 수 있습니다. 해당 엔티티를 저장한 이후
     */
    @PostPersist
    public void initHst() {
        log.info("insert 이후  ***** postPersist 한다!!!");

        YooSaltbInit01Hst h = new YooSaltbInit01Hst();
        h.setSeq(1);
        h.setEventDscd("I");
        h.setTenantId(this.tenantId);
        h.setEnplcCd(this.enplcCd);
        h.setStoreCd(this.storeCd);
        h.setItemCd(this.itemCd);
        h.setStockQty(this.stockQty);
        h.setStockAmt(this.stockAmt);
        h.setSysRegId(this.sysRegId);
        this.addYooInitHst(h);

        log.info("Init01 insert 이후  ***** postPersist set 완료!!!");

    }

    @PreUpdate
    public void preUpdate() {
        log.info("Init01 PreUpdate 한다!!!");
        seq = yooInitHistories.get(0).getSeq();
    }

    /**
     * 해당 엔티티를 업데이트 한 이후
     */
    @PostUpdate
    public void updateHst() {
        log.info("Init01Hst PostUpdate 한다!!!");

        YooSaltbInit01Hst h = new YooSaltbInit01Hst();
        h.setSeq(seq+1);
        h.setEventDscd("U");
        h.setTenantId(this.tenantId);
        h.setEnplcCd(this.enplcCd);
        h.setStoreCd(this.storeCd);
        h.setItemCd(this.itemCd);
        h.setStockQty(this.stockQty);
        h.setStockAmt(this.stockAmt);
        h.setSysRegId(this.sysRegId);
        this.addYooInitHst(h);

        log.info("Init01 insert 이후  ***** postPersist set 완료!!!");
    }



}
