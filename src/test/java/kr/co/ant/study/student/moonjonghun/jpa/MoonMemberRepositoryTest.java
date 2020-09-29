package kr.co.ant.study.student.moonjonghun.jpa;

import java.util.Iterator;
import java.util.List;

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
import org.springframework.transaction.annotation.Transactional;

import kr.co.ant.study.student.moonjonghun.jpa.DAO.MoonInit;
import kr.co.ant.study.student.moonjonghun.jpa.DAO.MoonInitHst;

@EntityScan(basePackages = "kr.co.ant.study.student.moonjonghun.jpa")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(MoonMemberRepository.class)
@ActiveProfiles("mjh")
@Rollback(false)
@Transactional(rollbackFor = Exception.class)
class MoonMemberRepositoryTest {

	@Autowired
	private MoonMemberRepository repository;
	
	@Autowired
	private EntityManager manager;
	
//	@Test
//	void test() {
//		MoonMember member= new MoonMember("moon", "문종훈");
//		repository.save(member);
//	}
	
	/**
	 * 데이터 추가 테스트 
	 * @author : moonjonghun
	 * @apiNote : main data를 remove 하고 audit data : EVENT_DSCD ('I') insert 
	 * */
//	@Test
	void testInit() {
		MoonInit init = new MoonInit();
		
		init.setTenantId("E68");
		init.setEnplcCd("G001");
		init.setStoreCd("V1");
		init.setItemCd("민트마카롱");
		init.setStockAmt(1.0003);
		init.setStockQty(1.00003);
		init.setSysRegId("assssss");
		init.setSysUpdId("assssss");
		
		// ###트리거의 로직을 구현해보자###
		// init이 insert된 이후에 반복문을 돌면서 
		// hst table에 insert하자
		
		MoonInitHst initHst = new MoonInitHst();
		initHst.setSeq(1);
		initHst.setEventDscd("I");
		initHst.setTenantId(init.getTenantId());
		initHst.setEnplcCd(init.getEnplcCd());
		initHst.setStoreCd(init.getStoreCd());
		initHst.setItemCd(init.getItemCd());
		initHst.setStockQty(init.getStockQty());
		initHst.setStockAmt(init.getStockAmt());
		initHst.setSysRegId(init.getSysRegId());
		initHst.setSysUpdId(init.getSysUpdId());
		
		// 여기서 initHst 객체는 부모객체정보가 비어있는 상태이다
		init.addMoonInitHst(initHst);
		try {
			//insert
			repository.save(init);
			manager.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 데이터 삭제 테스트 
	 * @author : moonjonghun
	 * @apiNote : main data를 remove 하고 audit data : EVENT_DSCD ('D') insert 
	 * */
//	@Test
	@Transactional
	public void removeTest() {
		try {
			MoonInit init = new MoonInit();
			init.setInitId(1L);
			MoonInit data= (MoonInit) repository.select(init, init.getInitId());
			List<MoonInitHst> hstList = data.getMoonInitHistories();
			
			//max seq 구하기
			Iterator<MoonInitHst> iter = hstList.iterator();
			int maxSeq = 0;
			while(iter.hasNext()) {
				MoonInitHst hst = (MoonInitHst) iter.next();
				if(hst.getSeq() >= maxSeq) {
					maxSeq = hst.getSeq();
				};
				
			}
			
			//HST 정보를 셋팅 해두고 data를 삭제후 HST를 insert
			MoonInitHst dHst = new MoonInitHst();
			dHst.setTenantId(data.getTenantId());
			dHst.setEnplcCd(data.getEnplcCd());
			dHst.setSeq(maxSeq+1);
			dHst.setEventDscd("D");
			dHst.setStoreCd(data.getStoreCd());
			dHst.setItemCd(data.getItemCd());
			dHst.setStockAmt(data.getStockAmt());
			dHst.setStockQty(data.getStockQty());
			
			dHst.setSaltbInit01(data);
			dHst.setSysRegId(data.getSysRegId());
			dHst.setSysUpdId(data.getSysUpdId());

			//############1차시도...#####################
			//자식쪽에 event_dscd 가 'D' 인 history insert
			repository.save(dHst);
			
//			//부모Entity에서 자식 Entity 연관관계 끊기
//			data.setMoonInitHistories(null);
//			
//			//부모 Entity 상태 저장 persist context에 아직 올라가있는상태임
//			repository.save(data);
//			manager.flush();
//			
//			//삭제
//			repository.removeData(data);
			
			//############2차시도...#####################
			//연관관계의 주인이 자식쪽에 있기때문에 자식쪽에서 부모를 삭제해주어야한다.
			repository.removeData(dHst.getSaltbInit01());
			//이것도 FK때문에 안된다.
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 데이터 업데이트 테스트 
	 * @author : moonjonghun
	 * @apiNote : main data를 update 하고 audit data : EVENT_DSCD ('D','I') insert 
	 * */
//	@Test
	public void updateTest() {
		MoonInit init = new MoonInit();
		init.setInitId(1L);
		//조회되었으니 persist계층에서 managed.

		//AS-IS Data
		MoonInit data = (MoonInit) repository.select(init, init.getInitId());
		List<MoonInitHst> hstList = data.getMoonInitHistories();
		
		Iterator iter = hstList.iterator();
		int maxSeq = 0;
		while(iter.hasNext()) {
			MoonInitHst hst = (MoonInitHst) iter.next();
			if(hst.getSeq() >= maxSeq) {
				maxSeq = hst.getSeq();
			};
			
		}

		MoonInitHst dHst = new MoonInitHst();
		
		dHst.setEventDscd("D");
		dHst.setSeq(maxSeq + 1 );
		dHst.setTenantId(data.getTenantId());
		dHst.setEnplcCd(data.getEnplcCd());
		dHst.setStoreCd(data.getStoreCd());
		dHst.setItemCd(data.getItemCd());
		dHst.setStockAmt(data.getStockAmt());
		dHst.setStockQty(data.getStockQty());
		dHst.setSysRegId(data.getSysRegId());
		dHst.setSysUpdId(data.getSysUpdId());
		dHst.setSaltbInit01(null);

		data.addMoonInitHst(dHst);
		
		MoonInitHst iHst = new MoonInitHst();
		
		iHst.setEventDscd("I");
		iHst.setSeq(maxSeq + 2 );
		iHst.setTenantId(data.getTenantId());
		iHst.setEnplcCd(data.getEnplcCd());
		iHst.setStoreCd(data.getStoreCd());
		iHst.setItemCd(data.getItemCd());
		iHst.setStockAmt(data.getStockAmt());
		iHst.setStockQty(data.getStockQty());
		iHst.setSysRegId(data.getSysRegId());
		iHst.setSysUpdId(data.getSysUpdId());
		iHst.setSaltbInit01(null);

		data.addMoonInitHst(iHst);
	}
	
}
