package kr.co.ant.study.jpa.jpql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.ant.study.jpa.jpql.domain.BaseStockHistory;

public interface BaseStockHistoryRepository extends JpaRepository<BaseStockHistory, Long>{
	

}
