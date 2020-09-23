package kr.co.ant.study.student.moonjonghun.jpa.DAO;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "saltb_init01")
@Getter
@Setter
public class MoonInit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "init_id", nullable = false, length = 19, columnDefinition = "BIGINT unsigned", insertable = true)
	private long initId;
	
	@Column(name = "tenant_id", nullable = false, length = 27, columnDefinition = "VARCHAR")
	private String tenantId;
	
	@Column(name = "enplc_cd", nullable = false, length = 4, columnDefinition = "VARCHAR")
	private String enplcCd;
	
	@Column(name = "store_cd", nullable = false, length= 2, columnDefinition = "VARCHAR")
	private String storeCd;
	
	@Column(name = "item_cd", nullable = false, length = 30, columnDefinition = "VARCHAR")
	private String itemCd;
	
	@Column(name = "stock_qty", nullable = false, length = 20, scale = 5, precision = 5, columnDefinition = "DECIMAL default '0.00000'")
	private double stockQty;
	
	@Column(name = "stock_amt", nullable = false, length = 25, scale = 5, precision = 5, columnDefinition = "DECIMAL default '0.00000'")
	private double stockAmt;
	
	@Column(name = "sys_reg_id", nullable = false, length = 27, columnDefinition = "VARCHAR")
	private String sysRegId;
	
	
	private LocalDateTime sysRegDate;
	
	@Column(name = "sys_upd_id", nullable = false, length = 27, columnDefinition = "VARCHAR")
	private String sysUpdId;
	
	
	private LocalDateTime sysUpdDate;
	
}
