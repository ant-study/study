package kr.co.ant.study.jpa.jpql.listner;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.ant.study.jpa.jpql.domain.BaseStock;
import kr.co.ant.study.jpa.jpql.domain.BaseStockHistory;
import kr.co.ant.study.jpa.jpql.repository.BaseStockHistoryRepository;

@Component
public class BaseStockListner{

	@Autowired
	private BaseStockHistoryRepository historyRepository;
	
	@PostPersist
	public void onPersiste(Object o) {
		saveHistory(o, "I");
	}
	
	@PostUpdate
	public void onUpdate(Object o) {
		saveHistory(o, "U");
	}
	
	@PostRemove
	public void onDelete(Object o) {
		saveHistory(o, "D");
	}
	
	private void saveHistory(Object o, String eventName) {
		if(o instanceof BaseStock) {
			BaseStock stock = (BaseStock)o;
			BaseStockHistory history = new BaseStockHistory();
			history.setBaseStockHistory(stock, eventName);
			historyRepository.save(history);
		}
	}
}
