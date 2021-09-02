package kr.co.ant.study.jpa.basic;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import kr.co.ant.study.jpa.basic.domain.Member;
import kr.co.ant.study.jpa.basic.dto.MemberDTO;
import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@EntityScan(basePackages = "kr.co.ant.study.jpa")
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(MemberRepository.class)
@ActiveProfiles("hks")
@Rollback(false)
@Slf4j
class JPQLTest {

	@Autowired
	private MemberRepository repository;
	
	@Autowired
	private EntityManager manager;
	
	/**
	 * select * from member where id = 'test1'
	 */
	@Test
	@Rollback(false)
	void testJPQLFind() {
		String jpql = "from Member m where m.id = ?1";
		TypedQuery<Member> a = manager.createQuery(jpql, Member.class);
		a.setParameter(1, "test1");
		log.info("Result ::: {}", a.getSingleResult().getName());
	}
	
	/**
	 * select * from member where id = 'test1'
	 */
	@Test
	@Rollback(false)
	void testJPQLFindForProjection() {
		String jpql = "select new kr.co.ant.study.jpa.basic.dto.MemberDTO(m.name, m.addrYn) from Member m where m.id = ?1";
		TypedQuery<MemberDTO> a = manager.createQuery(jpql, MemberDTO.class);
	
		a.setParameter(1, "test1");
		log.info("Result ::: {}", a.getSingleResult().getName());
		log.info("Result ::: {}", a.getSingleResult().getAddrYn());
	}
	
	
}
