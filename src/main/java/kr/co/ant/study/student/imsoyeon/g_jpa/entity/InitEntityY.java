package kr.co.ant.study.student.imsoyeon.g_jpa.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY) @Column(length = 19)
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
}
