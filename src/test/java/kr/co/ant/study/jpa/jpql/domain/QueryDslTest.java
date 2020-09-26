package kr.co.ant.study.jpa.jpql.domain;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import kr.co.ant.study.jpa.jpql.repository.BaseStockQueryDslRepository;
import kr.co.ant.study.jpa.jpql.repository.BaseStockRepository;
import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@EntityScan(basePackages = "kr.co.ant.study.jpa")
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("hks")
@Rollback(false)
@Import(BaseStockQueryDslRepository.class)
@Slf4j
class QueryDslTest {
	
	@Autowired
	BaseStockQueryDslRepository repository;
	
	
	@Test
	void 페치조인_jpql() {
		BaseStock stock = repository.fetchJoinWithQueryDsl(1L, BigDecimal.valueOf(10L));
		log.info("Length :: {} ", stock.getBaseStockHistories().size());
	}

}
