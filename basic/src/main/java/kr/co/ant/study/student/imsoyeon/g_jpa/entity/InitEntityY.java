package kr.co.ant.study.student.imsoyeon.g_jpa.entity;

import java.math.BigDecimal;
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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * Unique 컬럼 2개 이상 설정하기
 * 		@Table(~, uniqueConstraints = {@UniqueConstraint(columnNames = {"tenantId","enplcCd", ..})})
 *  	** @Column( ~, unique=true)로는 하나만 가능하대
 * */
@Entity
@Table(name = "saltb_init01_y", uniqueConstraints = {@UniqueConstraint(columnNames = {"tenantId","enplcCd","storeCd","itemCd"})})
@DynamicUpdate	//	value가 변경된 컬럼만 update쳐라
@Getter @Setter @ToString
public class InitEntityY {
	
	/*
	 * Entity끼리 관계를 맺어줘야 한다.
	 * 관계 맺을 클래스끼리 각각 단방향 관계 설정 → 양방향관계
	 * 관계 in (1:N, N:1, N:N)
	 * 관계를 맺을때, 관련키(외래키)를 관리할 주인 클래스와 실제 외래키를 가지고 있을 클래스를 정할 수 있음
	 * 1:N, N:1 경우 1이 주인.
	 * 
	 * 관계 혹은 join컬럼 속성으로 디테일한 부분 컨트롤 가능
	 * 		- 맵핑된 주인 클래스를 명시 : mappedBy
	 * 		- 주인을 select해올 때, 하위 클래스도 동시에 select할 것인지 여부 : FetchType LAZY / EAGER (메모리 고려하기)
	 * 		- CRUD 등에 대한 cascade 관리 : CascadeType PERSIST / MERGE / REMOVE / DETACH 등 
	 * 		- 현재 Entity를 insert/update할 때, 해당 필드도 같은 작업을 진행할지 여부 : insertable & updatable true / false
	 * 
	 * */
//	지연로딩으로(init01하나에 hst N개 있으니까 - 바로 select해오지 않는 것일 뿐, get해서 꺼낼 수 있음. get할 때 select문이 실행된다.).
	@OneToMany(mappedBy = "initEntityY", cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REMOVE})	//, fetch = FetchType.LAZY
//	@JoinColumn(name = "init_id")
	private List<InitHstEntityY> initHstEntityY;
	
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY) @Column(nullable = false, length = 19)
	private Long initId;
	
	@Column(nullable = false, length = 27)
	private String tenantId;
	
	@Column(nullable = false, length = 4)
	private String enplcCd;
	
	@Column(nullable = false, length = 2)
	private String storeCd;
	
	@Column(nullable = false, length = 30)
	private String itemCd;
	
	@Column(nullable = false, precision = 20, scale = 5)
	private BigDecimal stockQty;
	
	@Column(nullable = false, precision = 25, scale = 5)
	private BigDecimal stockAmt;
	
	@Column(nullable = false, length = 27)
	private String sysRegId;
	
	@Column(nullable = false)
	@CreationTimestamp
	private LocalDateTime sysRegDate;
	 
	@Column(nullable = true, length = 27)
	private String sysUpdId;
	
	@Column(nullable = true)
	private LocalDateTime sysUpdDate;
	
//	자신 관계인 클래스 담아둬야함
	public void addHistory(InitHstEntityY hst) {
		if (initHstEntityY == null) {
			initHstEntityY =  new ArrayList<InitHstEntityY>();
		}
		hst.setInitEntityY(this);
		initHstEntityY.add(hst);
	}
}
