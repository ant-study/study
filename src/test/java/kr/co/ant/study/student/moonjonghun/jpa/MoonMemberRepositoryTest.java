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
		init.setStockAmt(1.0001);
		init.setStockQty(1.00001);
		init.setSysRegId("assssss");
		init.setSysUpdId("assssss");
		
		// ###트리거의 로직을 구현해보자###
		// init이 insert된 이후에 반복문을 돌면서 
		// hst table에 insert하자
		
		MoonInitHst initHst = new MoonInitHst();
//		initHst.setSeq(2);
		initHst.setEventDscd("U");
		initHst.setTenantId("E68");
		initHst.setEnplcCd("G001");
		initHst.setStoreCd("V1");
		initHst.setItemCd("민트마카롱");
		initHst.setStockQty(1.00001);
		initHst.setStockAmt(1.0001);
		initHst.setSysRegId("assssss");
		initHst.setSysUpdId("assssss");
		
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
	@Transactional(readOnly = true)
	public void removeTest() {
//		MoonInitHst initHst = new MoonInitHst();
		try {
			//조회할때 new 하면 안되는건가?
			//아니야 상관없어 jpa가 구분할 수 있도록 별칭이 주어진것 뿐이야
			
			//연계 테이블의 값을 삭제하기 위해서는 subtable 의 값을 먼저 삭제하고
			//main테이블의 값을 삭제해야한다. 
			//table에 제약조건 객체가 묶여있다면 이를 null로 만들어줘야 삭제가 가능하다.
			//그럼 subtable의 데이터는 동일성이 유지되는가
			
			MoonInit init = new MoonInit();
			init.setInitId(1L);
			MoonInit data= (MoonInit) repository.removeToUpperData(init, 1L);
			data.getMoonInitHistories();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 데이터 업데이트 테스트 
	 * @author : moonjonghun
	 * @apiNote : main data를 update 하고 audit data : EVENT_DSCD ('D','I') insert 
	 * */
	@Test
	public void updateTest() {
		MoonInit init = new MoonInit();
		init.setInitId(1L);
		//조회되었으니 persist계층에서 managed.
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
		
//		MoonInitHst dHst = new MoonInitHst();
		
//		dHst.setEventDscd("D");
//		dHst.setSeq(maxSeq + 1 );
//		dHst.setTenantId(data.getTenantId());
//		dHst.setEnplcCd(data.getEnplcCd());
//		dHst.setStoreCd(data.getStoreCd());
//		dHst.setItemCd(data.getItemCd());
//		dHst.setStockAmt(data.getStockAmt());
//		dHst.setStockQty(data.getStockQty());
//		dHst.setSysRegId(data.getSysRegId());
//		dHst.setSysUpdId(data.getSysUpdId());
//		dHst.setSaltbInit01(null);
//		hstList.add(dHst);
//		data.addMoonInitHst(dHst);
		
//		MoonInitHst iHst = new MoonInitHst();
		
//		iHst.setEventDscd("I");
//		iHst.setSeq(maxSeq + 2 );
//		iHst.setTenantId(data.getTenantId());
//		iHst.setEnplcCd(data.getEnplcCd());
//		iHst.setStoreCd(data.getStoreCd());
//		iHst.setItemCd(data.getItemCd());
//		iHst.setStockAmt(data.getStockAmt());
//		iHst.setStockQty(data.getStockQty());
//		iHst.setSysRegId(data.getSysRegId());
//		iHst.setSysUpdId(data.getSysUpdId());
//		iHst.setSaltbInit01(null);
//		hstList.add(iHst);
//		data.addMoonInitHst(iHst);
		try {
			repository.update(data);
//			manager.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		repository.update(init);
		
	}
	
}
