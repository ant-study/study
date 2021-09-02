package kr.co.ant.study.jpa.jpql.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;

import kr.co.ant.study.jpa.jpql.domain.BaseStock;
import kr.co.ant.study.jpa.jpql.domain.QBaseStock;
import kr.co.ant.study.jpa.jpql.domain.QBaseStockHistory;
import kr.co.ant.study.jpa.jpql.dto.BaseStockDTO;
import kr.co.ant.study.jpa.jpql.dto.BaseStockHistoryDTO;

@Repository
public class BaseStockQueryDslRepository extends QuerydslRepositorySupport implements Base1StockRepository{

	public BaseStockQueryDslRepository() {
		super(BaseStock.class);
	}
	
	
	public BaseStock fetchJoinWithQueryDsl(Long id, BigDecimal qty) {
		
		QBaseStock stock = QBaseStock.baseStock;
		QBaseStockHistory history = QBaseStockHistory.baseStockHistory;
		
		return from(stock)
			.join(stock.baseStockHistories)
			.fetchJoin()
			.where(stock.initId.eq(id)
					.and(history.stockQty.lt(qty)))
			.fetchOne();
			
		
	}
	
	public BaseStockDTO projectionForSingle(Long id, BigDecimal qty) {
		
		QBaseStock stock = QBaseStock.baseStock;
		QBaseStockHistory history = QBaseStockHistory.baseStockHistory;
		
		return from(stock)
				.select(Projections.bean(BaseStockDTO.class, stock.initId, stock.enplcCd, stock.itemCd, stock.stockAmt, stock.stockQty, stock.storeCd))
				.where(stock.initId.eq(id))
				.fetchOne();
				
	}
	
	public BaseStockDTO projectionForChild(Long id, BigDecimal qty) {
		
		QBaseStock stock = QBaseStock.baseStock;
		QBaseStockHistory history = QBaseStockHistory.baseStockHistory;
		
		return from(stock)
				.join(stock.baseStockHistories, history)
				.where(stock.initId.eq(id)
						.and(history.stockQty.lt(qty)))
				.transform(GroupBy.groupBy(stock.initId)
						.as(Projections.bean(BaseStockDTO.class, stock.initId, stock.enplcCd, stock.itemCd, stock.stockAmt, stock.stockQty, stock.storeCd
								, GroupBy.list(Projections.bean(BaseStockHistoryDTO.class, history.enplcCd)
										).as("baseStockHistories")
								)
							)
						).get(id);
	}
	
}
