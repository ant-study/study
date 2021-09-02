package kr.co.ant.study.jpa.jpql.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "saltb_init01_hst_y")
@DynamicUpdate	//	value가 변경된 컬럼만 update쳐라
@Getter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BaseStockHistory {
	
	@ManyToOne
	@JoinColumn(name = "init_id", nullable = true)	//
	private BaseStock baseStock;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(length = 25)	//@@
	@EqualsAndHashCode.Include
	private Long initHstId;
	
	@Column(nullable = false, length = 19)	//@@
	private int seq;
	
	@Column(name="event_dscd", nullable = false, length = 1)		//@@
	private String eventDscd;
	
	@Column(nullable = false, length = 27)
	private String tenantId;
	
	@Column(nullable = false, length = 4)
	private String enplcCd;
	
	@Column(nullable = false, length = 2)
	private String storeCd;
	
	@Column(nullable = false, length = 30)
	private String itemCd;
	
	@Column(nullable = false, precision = 20, scale = 5)	//, columnDefinition = "DEFAULT '0.00000'"
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
	@UpdateTimestamp
	private LocalDateTime sysUpdDate;
	
	public void setBaseStockHistory(BaseStock baseStock, String eventName) {
		this.eventDscd = eventName;
		this.baseStock = baseStock;
		this.tenantId = baseStock.getTenantId();
		this.enplcCd = baseStock.getEnplcCd();
		this.storeCd = baseStock.getStoreCd();
		this.itemCd = baseStock.getItemCd();
		this.stockQty = baseStock.getStockQty();
		this.stockAmt =baseStock.getStockAmt();
		this.sysRegId = baseStock.getSysRegId();
		this.sysUpdId = baseStock.getSysUpdId();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseStockHistory other = (BaseStockHistory) obj;
		if (initHstId == null) {
			if (other.initHstId != null)
				return false;
		} else if (!initHstId.equals(other.initHstId))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((initHstId == null) ? 0 : initHstId.hashCode());
		return result;
	}
	
	
	
}
