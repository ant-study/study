package kr.co.ant.study.student.seomyeongjoo.jpa.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="saltb_init01")
@Getter @Setter @ToString
@Slf4j
public class Init01 extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="init_id")
    private Long idx;

    private String storeCd;

    private BigDecimal stockQty = new BigDecimal("0.00000");

    private BigDecimal stockAmt = new BigDecimal("0.00000");

    //양방향이면 삭제의 경우 부모테이블은 삭제되고 자식 테이블은 존재할 수가 없으므로
    //단반향으로 변경 부모 -> 자식 부모는 자식 테이블을 참조하지만 자식은 부모 테이블을 참조 안함.
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "init_id")
    private List<Init01History> init01HistoryList;

    public void addInit01Hst(Init01History hst){
        if(init01HistoryList == null){
            init01HistoryList = new ArrayList<Init01History>();
        }

        hst.setInitId(this.idx);
        init01HistoryList.add(hst);
    }

    @PreUpdate
    public void updateable() {
        log.info("Init01 Update 한다!!!");
    }

    @PrePersist
    public void prePersist() {
        log.info("Init01 prePersist 한다!!!");
    }


}
