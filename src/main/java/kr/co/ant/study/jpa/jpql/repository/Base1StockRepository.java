package kr.co.ant.study.jpa.jpql.repository;

import java.math.BigDecimal;

import kr.co.ant.study.jpa.jpql.domain.BaseStock;

public interface Base1StockRepository {
	public BaseStock fetchJoinWithQueryDsl(Long id, BigDecimal qty);
}
