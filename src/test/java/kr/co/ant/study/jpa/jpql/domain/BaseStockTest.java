package kr.co.ant.study.jpa.jpql.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import kr.co.ant.study.jpa.basic.MemberRepository;
import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@EntityScan(basePackages = "kr.co.ant.study.jpa")
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(MemberRepository.class)
@ActiveProfiles("hks")
@Rollback(false)
@Slf4j
class BaseStockTest {
	
	@Autowired
	private EntityManager manager;
	
	@Test
	void testData() {
		BaseStock baseStock = new BaseStock();
		baseStock.setTenantId("T078");
		baseStock.setEnplcCd("E001");
		baseStock.setStoreCd("S1");
		baseStock.setItemCd("IT004");
		baseStock.setStockQty(new BigDecimal(10.00000));
		baseStock.setStockAmt(new BigDecimal(10000.00000));
		baseStock.setSysRegId("U077");
		
		for(int i = 0 ; i < 20 ; i++) {
			BigDecimal qty = new BigDecimal(i+1);
			baseStock.setStockQty(qty);
			baseStock.addHistory("U");
		}
		manager.persist(baseStock);
	}
	
	/**
	 * 수정삭제 자식 persis가 안된다
	 * 다른방법 고민
	 */
	@Test
	void 수정() {
		BaseStock stock = manager.find(BaseStock.class, 1L);
		stock.setItemCd("TEST04");
	}
	@Test
	void 삭제() {
		BaseStock stock = manager.find(BaseStock.class, 1L);
		manager.remove(stock);
	}
	
	@Test
	void 기본조회_jpql() {
		String sql = "select s "
					+ "from BaseStock s "
					+ "where s.initId = :id";
		BaseStock stock = manager.createQuery(sql, BaseStock.class)
			.setParameter("id", 1L)
			.getSingleResult();
		log.info("Result :: {}", stock.getInitId());
	}
	
	@Test
	void 조인_jpql() {
		String sql = "select s "
				+ "from BaseStock s "
				+ "join s.baseStockHistories h "
				+ "where s.initId = :id";
		BaseStock stock = manager.createQuery(sql, BaseStock.class)
			.setParameter("id", 1L)
			.getSingleResult();
		log.info("Result :: {}", stock.getInitId());
		
		String sql2 = "select s "
				+ "from BaseStock s "
				+ "join BaseStockHistory h "
				+ "on s.initId = h.baseStock.initId "
				+ "where s.initId = :id";
		BaseStock stock2 = manager.createQuery(sql2, BaseStock.class)
			.setParameter("id", 1L)
			.getSingleResult();
		log.info("Result :: {}", stock2.getInitId());
	}
	@Test
	void 페치조인_jpql() {
		String sql = "select s "
				+ "from BaseStock s "
				+ "join s.baseStockHistories h "
				+ "where s.initId = :id "
				+ "and h.stockQty < 10 ";
		BaseStock stock = manager.createQuery(sql, BaseStock.class)
			.setParameter("id", 1L)
			.getSingleResult();
		log.info("그냥 조인 결과 :: {}", stock.getBaseStockHistories().size());
		manager.clear();
		String sql2 = "select s "
				+ "from BaseStock s "
				+ "join fetch s.baseStockHistories h "
				+ "where s.initId = :id "
				+ "and h.stockQty < 10 ";
		BaseStock stock2 = manager.createQuery(sql2, BaseStock.class)
			.setParameter("id", 1L)
			.getSingleResult();
		log.info("페치 조인 결과 :: {}", stock2.getBaseStockHistories().size());
	}

}
