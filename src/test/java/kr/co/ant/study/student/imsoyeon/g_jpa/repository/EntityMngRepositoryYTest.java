package kr.co.ant.study.student.imsoyeon.g_jpa.repository;

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

import kr.co.ant.study.student.imsoyeon.g_jpa.entity.InitEntityY;
import kr.co.ant.study.student.imsoyeon.g_jpa.entity.InitHstEntityY;
import lombok.extern.slf4j.Slf4j;

@EntityScan(basePackages = "kr.co.ant.study.student.imsoyeon.g_jpa")
@DataJpaTest	//	JPA 관련 설정 로딩
@AutoConfigureTestDatabase(replace = Replace.NONE)	//	Test Database 사용안함
@Import(EntityMngRepositoryY.class)	//	Repository Annotation은 Jpa 관련 설정이 아니라 DataJpaTest시에 Bean으로 등록되지 않기 때문에 Import로 강제 등록
@ActiveProfiles("isy")	//	자신이 설정한 application-이니셜.yml을 로드하기 위해 Profile 설정
@Rollback(false)	//	기본 Rollback으로 되어 있음 Rollback true이면 commit시 생성하는 쿼리가 실행되지 않기 때문에 공부할 때는 귀찮아도 false로 해서 테스트
@Slf4j
class EntityMngRepositoryYTest {

	@Autowired
	private EntityMngRepositoryY repository;
	
	@Test
	@Rollback(false)
	void manageEntity01() {
		try {
//			init01
			InitEntityY init01 = new InitEntityY();
			init01.setTenantId("T078");
			init01.setEnplcCd("E001");
			init01.setStoreCd("S1");
			init01.setItemCd("IT001");
			init01.setStockQty(new BigDecimal(10.00000));
			init01.setStockAmt(new BigDecimal(10000.00000));
			init01.setSysRegId("U077");
			
//			hst
			InitHstEntityY hst = new InitHstEntityY();
//			hst.setInitId();
			hst.setEventDscd("I");
			hst.setSeq(1);
			hst.setTenantId(init01.getTenantId());
			hst.setEnplcCd(init01.getEnplcCd());
			hst.setStoreCd(init01.getStoreCd());
			hst.setItemCd(init01.getItemCd());
			hst.setStockQty(init01.getStockQty());
			hst.setStockAmt(init01.getStockAmt());
			hst.setSysRegId(init01.getSysRegId());
			hst.setSysRegDate(init01.getSysRegDate());
			hst.setSysUpdId(init01.getSysUpdId());
			hst.setSysUpdDate(init01.getSysUpdDate());
			
			 init01.addHistory(hst);
			repository.save(init01);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
//	@Rollback(false)
//	void testMng() {
//		try {
//			MemberY mem = new MemberY();
//			mem.setMemberId("이름");
//			mem.setName("spdlsdasd");
//			em.persist(mem);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
