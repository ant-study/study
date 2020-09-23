package kr.co.ant.study.jpa.basic;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import kr.co.ant.study.jpa.basic.domain.Member;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(MemberRepository.class)
@ActiveProfiles("hks")
@Rollback(false)
class MemberRepositoryTest {

	@Autowired
	private EntityManager manager;
	
	/**
	 * persist entity관리 대상으로 포함된다.
	 * transaction 종료 시점에 persist 객체를 DB에 insert 한다.
	 */
	@Test
	@Rollback(false)
	void testPersist() {
		Member m = new Member();
		m.setMemberId("tt");
		m.setName("테스트");
		manager.persist(m);
	}
	
	
	/**
	 * persist 관리로 포함시켰다가 다시 때버린다.
	 * commit 시점에는 때버린 상태니 관리목록에 존재 하지 않아 insert를 하지 않는다.
	 */
	@Test
	void testDetech() {
		Member m = new Member();
		m.setMemberId("test3");
		m.setName("테스트");
		manager.persist(m);
		manager.detach(m);
	}
	
	/**
	 * persist 관리로 포함하고 transaction 끝나기전에 flush로 insert를 한다.
	 * detach로 때어도 이미 insert가 된 상태이고 commit될때 DB에 insert 된다.
	 */
	@Test
	void testDetechBeforeFlush() {
		Member m = new Member();
		m.setMemberId("tt5");
		m.setName("테스트");
		manager.persist(m);
		manager.flush();
		manager.detach(m);
	}
	
	/**
	 * persist 관리로 들어간후에 Data를 수정하면 update가 된다.
	 * insert into t_member (addr_yn, name, member_id) values (?, ?, ?)
     * update t_member set addr_yn=?, name=? where member_id=?
	 */
	@Test
	void testModify() {
		Member m = new Member();
		m.setMemberId("tt6");
		m.setName("테스트");
		manager.persist(m);
		m.setName("테스트2");
	}
	
	/**
	 * detach된 상태에서 entity를 수정해도 업데이트가 안된다.
	 * detach된 entity를 merge하면 DB에 해당 Data를 다시 불러와 Persist상태로 만들고
	 * merge시에 Persist상태의 객체와 Merge대상 entity의 변경사항을 감지(dirty check) 한후에 변경 값이 있는경우 업데이트 한다.
	 */
	@Test
	void testDetechAfterModifyAndMerge() {
		Member m = new Member();
		m.setMemberId("tt8");
		m.setName("테스트");
		manager.persist(m);
		manager.flush();
		manager.detach(m);
		m.setName("테스트222");
		manager.merge(m);
	}
	

	/**
	 * select가 몇번 일어날까?
	 */
	@Test
	void testPersistAndFind() {
		Member m = new Member();
		m.setMemberId("t12");
		m.setName("테스트");
		manager.persist(m);
		manager.flush();;
		Member b = manager.find(Member.class, "t12");
		System.out.println(b.getName());
	}

	@Test
	void testFind(){
		Member  m = manager.find(Member.class, "test1");
	}

}
