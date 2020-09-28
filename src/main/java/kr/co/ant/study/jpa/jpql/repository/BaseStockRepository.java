package kr.co.ant.study.jpa.jpql.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ant.study.jpa.jpql.domain.BaseStock;

@Repository
public class BaseStockRepository {

	@Autowired
	private EntityManager em;
	
	
	public BaseStock findTest(Long id) {
		String sql = "select s "
				+ "from BaseStock s "
				+ "join s.baseStockHistories h "
				+ "where s.initId = :id "
				+ "and h.stockQty < 10 ";
		return em.createQuery(sql, BaseStock.class)
			.setParameter("id", 1L)
			.getSingleResult();
	}
	
	public BaseStock findFetchTest(Long id) {
		String sql2 = "select s "
				+ "from BaseStock s "
				+ "join fetch s.baseStockHistories h "
				+ "where s.initId = :id "
				+ "and h.stockQty < 10 ";
		return em.createQuery(sql2, BaseStock.class)
			.setParameter("id", 1L)
			.getSingleResult();
	}
}
