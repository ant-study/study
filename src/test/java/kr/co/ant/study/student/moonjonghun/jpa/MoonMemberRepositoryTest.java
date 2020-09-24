package kr.co.ant.study.student.moonjonghun.jpa;

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

import kr.co.ant.study.student.moonjonghun.jpa.DAO.MoonInit;

@EntityScan(basePackages = "kr.co.ant.study.student.moonjonghun.jpa")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(MoonMemberRepository.class)
@ActiveProfiles("mjh")
@Rollback(false)
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
	
	@Test
	void testInit() {
		MoonInit init = new MoonInit();
		
		init.setTenantId("E68");
		init.setEnplcCd("G001");
		init.setStoreCd("V1");
		init.setItemCd("민트마카롱");
		init.setStockAmt(1.0001);
		init.setStockQty(1.00001);
		
		repository.save(init);
	}

}
