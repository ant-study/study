package kr.co.ant.study.jpa.jpql.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import kr.co.ant.study.jpa.jpql.listner.BaseStockListner;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "saltb_init01_y", uniqueConstraints = {@UniqueConstraint(columnNames = {"tenantId","enplcCd","storeCd","itemCd"})})
@DynamicUpdate
@Getter @Setter @ToString
@EntityListeners(BaseStockListner.class)
public class BaseStock {
	
	@OneToMany(mappedBy = "baseStock")	//, fetch = FetchType.LAZY
	private List<BaseStockHistory> baseStockHistories = new ArrayList<BaseStockHistory>();;
	
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
	
	public void addHistory(BaseStockHistory h) {
		baseStockHistories.add(h);
	}
	
	public void removeHistory(BaseStockHistory h) {
		baseStockHistories.remove(h);
	}
	
}
