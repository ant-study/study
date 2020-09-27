package kr.co.ant.study.student.seomyeongjoo.jpa.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="saltb_init01_hst")
@Getter @Setter @ToString
public class Init01History extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="init_hst_id")
    private Long idx;

    @Column(name = "init_id")
    private Long initId;

//    @ManyToOne(fetch = FetchType.LAZY, optional = true)
//    @JoinColumn(name = "init_id", nullable = true)
//    private Init01 init01;

    private int seq;

    private String eventDscd;

    private String storeCd;

    private String itemCd;

    private BigDecimal stockQty = new BigDecimal("0.00000");

    private BigDecimal stockAmt = new BigDecimal("0.00000");



}
