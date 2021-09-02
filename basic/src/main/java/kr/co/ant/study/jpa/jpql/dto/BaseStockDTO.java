package kr.co.ant.study.jpa.jpql.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import kr.co.ant.study.jpa.jpql.domain.BaseStockHistory;
import lombok.Setter;
import lombok.ToString;
import lombok.Getter;

@Getter @Setter @ToString
public class BaseStockDTO {
	
	private Long initId;
	
	private String tenantId;
	
	private String enplcCd;
	
	private String storeCd;
	
	private String itemCd;
	
	private BigDecimal stockQty;
	
	private BigDecimal stockAmt;
	
	private List<BaseStockHistory> baseStockHistories = new ArrayList<BaseStockHistory>();;
	
	
}
