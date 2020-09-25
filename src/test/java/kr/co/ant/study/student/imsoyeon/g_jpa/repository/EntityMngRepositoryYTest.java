package kr.co.ant.study.student.imsoyeon.g_jpa.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

import kr.co.ant.study.student.imsoyeon.g_jpa.entity.InitEntityY;
import kr.co.ant.study.student.imsoyeon.g_jpa.entity.InitHstEntityY;
import kr.co.ant.study.student.imsoyeon.g_jpa.entity.MemberY;
import kr.co.ant.study.student.imsoyeon.g_jpa.repository.EntityMngRepositoryY;
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
	
	@Autowired
	private EntityManager em;
	
//	@Test
	@Rollback(false)
	void manageEntity01() {
		try {
			InitEntityY init01 = new InitEntityY();
			init01.setTenantId("T078");
			init01.setEnplcCd("E001");
			init01.setStoreCd("S1");
			init01.setItemCd("IT001");
			init01.setStockQty(new BigDecimal(10.00000));
			init01.setStockAmt(new BigDecimal(10000.00000));
			init01.setSysRegId("U077");
			
			repository.save(init01);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Rollback(false)
	void manageEntity02() {		
		try {
			InitHstEntityY hst = new InitHstEntityY();
			hst.setInitId(1L);
			hst.setEventDscd("I");
			hst.setTenantId("T078");
			hst.setEnplcCd("E001");
			hst.setStoreCd("S1");
			hst.setItemCd("IT001");
			hst.setStockQty(new BigDecimal(10.00000));
			hst.setStockAmt(new BigDecimal(10000.00000));
			hst.setSysRegId("U077");
			
			repository.save(hst);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	@Rollback(false)
	void testMng() {
		try {
			MemberY mem = new MemberY();
			mem.setMemberId("이름");
			mem.setName("spdlsdasd");
			em.persist(mem);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
