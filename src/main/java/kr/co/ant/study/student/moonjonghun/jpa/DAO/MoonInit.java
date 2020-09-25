package kr.co.ant.study.student.moonjonghun.jpa.DAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "saltb_init01", uniqueConstraints = {@UniqueConstraint(name = "ux01_init", columnNames = {"tenant_id", "enplc_cd", "store_cd", "item_cd"})})
@SequenceGenerator(name = "saltb_init01_seq_gen", sequenceName = "saltb_init01_seq", initialValue = 1, allocationSize = 1)
public class MoonInit {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "saltb_init01_seq_gen")
	@Column(name = "init_id", nullable = false, columnDefinition = "BIGINT(19) unsigned", insertable = false, updatable = false)
	private long initId;
	
	@Column(name = "tenant_id", nullable = false, columnDefinition = "VARCHAR(27)")
	private String tenantId;
	
	@Column(name = "enplc_cd", nullable = false,  columnDefinition = "VARCHAR(4)")
	private String enplcCd;
	
	@Column(name = "store_cd", nullable = false, columnDefinition = "VARCHAR(2)")
	private String storeCd;
	
	@Column(name = "item_cd", nullable = false, columnDefinition = "VARCHAR(30)")
	private String itemCd;
	
	@Column(name = "stock_qty", nullable = false, columnDefinition = "DECIMAL(20,5) default '0.00000'")
	private double stockQty;
	
	@Column(name = "stock_amt", nullable = false, columnDefinition = "DECIMAL(25,5) default '0.00000'")
	private double stockAmt;
	
	@Column(name = "sys_reg_id", nullable = true, columnDefinition = "VARCHAR(27)")
	private String sysRegId;
	
	
	private LocalDateTime sysRegDate;
	
	@Column(name = "sys_upd_id", nullable = true, columnDefinition = "VARCHAR(27)")
	private String sysUpdId;
	
	//연관관계 1 : N관계 외래키 설정
	// 부모 Entity인 경우에 OneToMany로 자식 Entity list를 나열 , mappedBy 속성으로 자식Entity에 FK로 참조된 부모객체명을 쓴다.
	// cascade : 부모 Entity의 persist status가 변경되었을때 연결된 자식 Entity의 status의 수정 방향설정
	// orphanRemoval : 연결된 자식객체가 null이 되었을 경우 삭제할 것인지 여부
	// fatch : 부모 Entity를 조회하였을때 딸려있는 자식 Entity를 함께 조회할것인지 
	@OneToMany(mappedBy = "saltbInit01", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REMOVE})
	private List<MoonInitHst> moonInitHistories; 

	private LocalDateTime sysUpdDate;
	
	public void addMoonInitHst(MoonInitHst hist) {
		if(moonInitHistories == null) {
			moonInitHistories = new ArrayList<MoonInitHst>();
		}
		hist.setSaltbInit01(this);
		moonInitHistories.add(hist);
	}
	
}
