package kr.co.ant.study.jpa.jpql.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class BaseStockHistoryDTO {
	
	private Long initHstId;
	
	private int seq;
	
	private String eventDscd;
	
	private String tenantId;
	
	private String enplcCd;
	private String storeCd;
	
	private String itemCd;
	
	private BigDecimal stockQty;
	
	private BigDecimal stockAmt;
	
	
}
