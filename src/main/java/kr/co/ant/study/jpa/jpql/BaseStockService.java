package kr.co.ant.study.jpa.jpql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.ant.study.jpa.jpql.domain.BaseStock;
import kr.co.ant.study.jpa.jpql.repository.BaseStockRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BaseStockService {

	@Autowired
	private BaseStockRepository repository;
	
	public BaseStock findTest(Long id) {
		BaseStock stock = repository.findTest(id);
		return stock;
	}
	
	public BaseStock findFetchTest(Long id) {
		BaseStock stock = repository.findFetchTest(id);
		return stock;
	}
}
