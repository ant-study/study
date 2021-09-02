package kr.co.ant.study.jpa.jpql;

import kr.co.ant.study.jpa.jpql.domain.BaseStockHistory;

public class BaseContorller {
	private BaseStockService service;
	
	
	public void baseStock() {
		BaseStockHistory h = service.createHistory();
		service.addHistory(h);
	}

}
