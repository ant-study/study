package kr.co.ant.study.student.moonjonghun.jpa.DAO;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "saltb_init01_hst", uniqueConstraints = {@UniqueConstraint(name = "init_id", columnNames = {"init_id","seq","event_dscd"})})
@DynamicUpdate
@SequenceGenerator(name = "saltb_init01_hst_seq_gen", sequenceName = "saltb_init01_hst_seq", initialValue = 1, allocationSize = 1)
public class MoonInitHst {
	
	
	//연관관계 1 : N관계 외래키(FK) 설정
	//자식Entity인 경우에 ManyToOne으로 부모 Entity에 접근
	//JoinColumn으로 특별히 어느 컬럼과 연결할것인지 설정
	@ManyToOne
	@JoinColumn(name = "init_id" , nullable = false)
	private MoonInit saltbInit01;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "saltb_init01_hst_seq_gen")
	@Column(name = "init_hst_id", columnDefinition = "BIGINT(25) unsigned" , nullable = false)
	private long initHstId;
	
	@Column(name = "seq", columnDefinition = "INT(19) unsigned" , nullable = false)
	private int seq;
	
	@Column(name = "event_dscd", columnDefinition = "VARCHAR(1)" , nullable = false)
	private String eventDscd;
	
	@Column(name = "tenant_id", columnDefinition = "VARCHAR(27)" , nullable = false)
	private String tenantId;
	
	@Column(name = "enplc_cd", columnDefinition = "VARCHAR(4)" , nullable = false)
	private String enplcCd;
	
	@Column(name = "store_cd", columnDefinition = "VARCHAR(2)" , nullable = false)
	private String storeCd;
	
	@Column(name = "item_cd", columnDefinition = "VARCHAR(30)" , nullable = false)
	private String itemCd;
	
	@Column(name = "stock_qty", columnDefinition = "DECIMAL(20,5) DEFAULT '0.00000'" , nullable = false)
	private double stockQty;
	
	@Column(name = "stock_amt", columnDefinition = "DECIMAL(25,5) DEFAULT '0.00000'" , nullable = false)
	private double stockAmt;
	
	@Column(name = "sys_reg_id", columnDefinition = "VARCHAR(27)" , nullable = false)
	private String sysRegId;
	
	private LocalDateTime sysRegDate;
	
	@Column(name = "sys_upd_id", columnDefinition = "VARCHAR(27)" , nullable = true)
	private String sysUpdId;
	
	
	private LocalDateTime sysUpdDate;
	
}
