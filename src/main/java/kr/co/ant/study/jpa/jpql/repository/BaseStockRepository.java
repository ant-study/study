package kr.co.ant.study.jpa.jpql.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kr.co.ant.study.jpa.jpql.domain.BaseStock;

public interface BaseStockRepository extends JpaRepository<BaseStock, Long>{
	
	@Query(value = "select s from BaseStock s where s.initId = :initId")
	public BaseStock jqplQuery(Long initId);
	
	@EntityGraph(attributePaths = {"baseStockHistories"}, type = EntityGraphType.FETCH)
	public BaseStock findByInitIdAndBaseStockHistoriesStockQtyLessThan(Long id, BigDecimal stockQty);

}
