package kr.co.ant.study.jpa.jpql;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.ant.study.jpa.jpql.domain.BaseStock;
import kr.co.ant.study.jpa.jpql.domain.BaseStockHistory;
import kr.co.ant.study.jpa.jpql.repository.BaseStockRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BaseStockService {

	@Autowired
	private BaseStockRepository repository;
	
	@Autowired
	private EntityManager em;
	
	public BaseStock findTest(Long id) {
		BaseStock stock = repository.findTest(id);
		return stock;
	}
	
	public BaseStock findFetchTest(Long id) {
		BaseStock stock = repository.findFetchTest(id);
		return stock;
	}
	
	@Transactional
	public BaseStockHistory createHistory() {
		BaseStockHistory a = new BaseStockHistory();
		em.persist(a);
		BaseStock s = em.find(BaseStock.class, 1L);
		s.removeHistory(a);
		return a;
	}
	
	@Transactional
	public void addHistory(BaseStockHistory h) {
		BaseStock s = em.find(BaseStock.class, 1L);
		s.removeHistory(h);
	}
}
