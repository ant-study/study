package kr.co.ant.study.jpa.jpql.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.ant.study.jpa.jpql.BaseStockService;
import kr.co.ant.study.jpa.jpql.repository.BaseStockRepository;
import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@EntityScan(basePackages = "kr.co.ant.study.jpa")
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import({BaseStockService.class, BaseStockRepository.class})
@ActiveProfiles("hks")
@Rollback(false)
@Slf4j
class BaseStockTest {
	
	@Autowired
	private BaseStockService service;
	
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
		//연관관계로 조인시에는 자동으로 on이 추가되어 join된다.
		String sql = "select s "
				+ "from BaseStock s "
				+ "join s.baseStockHistories h "
				+ "where s.initId = :id";
		BaseStock stock = manager.createQuery(sql, BaseStock.class)
			.setParameter("id", 1L)
			.getSingleResult();
		log.info("Result :: {}", stock.getInitId());
		
		//연관관계이지만 각 Entity로 조인시에는 on에 join 조건을 걸어야 한다.
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
	void 조인_수량이10개_이하인것_조회_jpql() {
		
		String sql = "select s "
				+ "from BaseStock s "
				+ "join s.baseStockHistories h "
				+ "where s.initId = :id "
				+ "and h.stockQty < 10 ";
		BaseStock stock = manager.createQuery(sql, BaseStock.class)
			.setParameter("id", 1L)
			.getSingleResult();
		log.info("그냥 조인 결과 :: {}", stock.getBaseStockHistories().size());
		assertThat(stock.getBaseStockHistories().size()).isEqualTo(20);
	}
	
	
	@Test
	void 페치조인_수량이10개_이하인것_조회_jpql() {
		
		String sql2 = "select s "
				+ "from BaseStock s "
				+ "join fetch s.baseStockHistories h "
				+ "where s.initId = :id "
				+ "and h.stockQty < 10 ";
		BaseStock stock2 = manager.createQuery(sql2, BaseStock.class)
			.setParameter("id", 1L)
			.getSingleResult();
		log.info("페치 조인 결과 :: {}", stock2.getBaseStockHistories().size());
		assertThat(stock2.getBaseStockHistories().size()).isEqualTo(9);
	}
	
	@Test
	void 그냥조인과페치조인을_동일Transaction에서_실행_jpql() {
		조인_수량이10개_이하인것_조회_jpql();
		페치조인_수량이10개_이하인것_조회_jpql();
	}
	
	/**
	 * 문제
	 * 테스트코드 수정없이 Service, Repository 수정으로 테스트가 통과되도록 해보세요
	 * 단. 쿼리수정은 안됨
	 */
	@Test
	void 캐쉬_1차_jpql() {
		BaseStock stock = service.findTest(1L);
		assertThat(stock.getBaseStockHistories().size()).isEqualTo(20);
		
		BaseStock stock2 = service.findFetchTest(1L);
		assertThat(stock2.getBaseStockHistories().size()).isEqualTo(9);
	}
	
	

}
