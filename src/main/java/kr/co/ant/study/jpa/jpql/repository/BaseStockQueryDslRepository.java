package kr.co.ant.study.jpa.jpql.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import kr.co.ant.study.jpa.jpql.domain.BaseStock;
import kr.co.ant.study.jpa.jpql.domain.QBaseStock;
import kr.co.ant.study.jpa.jpql.domain.QBaseStockHistory;

@Repository
public class BaseStockQueryDslRepository extends QuerydslRepositorySupport{

	public BaseStockQueryDslRepository() {
		super(BaseStock.class);
	}
	
	
	public BaseStock fetchJoinWithQueryDsl(Long id, BigDecimal qty) {
		
		QBaseStock stock = QBaseStock.baseStock;
		QBaseStockHistory history = QBaseStockHistory.baseStockHistory;
		
		return from(stock)
			.join(stock.baseStockHistories, history)
			.fetchJoin()
			.where(stock.initId.eq(id)
					.and(history.stockQty.lt(qty)))
			.fetchOne();
			
		
	}

}
