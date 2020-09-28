/**
 * Author : yooS
 * Create Date : 2020. 9. 23.
 */
package kr.co.ant.study.student.songyoona.jpa;

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

import kr.co.ant.study.student.songyoona.jpa.domain.YooSaltbInit01;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 23.
 */
@EntityScan(basePackages = "kr.co.ant.study.student.songyoona.jpa")

@DataJpaTest //JPA 관련 설정 로딩
@AutoConfigureTestDatabase(replace = Replace.NONE) //Test Database 사용안함
@Import(YooMemberRepository.class) //Repository Annotation은 Jpa 관련 설정이 아니라 DataJpaTest시에 Bean으로 등록 되지 않기때문에 Import로 강제 등록
@ActiveProfiles("yoo") //자신이 설정한 application-이니셜.yml을 로드 하기 위해 Profile 설정
@Rollback(false) //기본 Rollback으로 되어 있음 Rollback true이면 commit시 생성하는 쿼리가 실행되지 않기 때문에 공부할때는 귀찮아도 false로 해서 테스트
class YooMemberRepositoryTest {


    @Autowired
    private EntityManager em;

    @Autowired
    private YooMemberRepository repo;

//    @Test
//    @Rollback(false)
//    void testPersist() {
//        YooMember m = new YooMember();
//        //m.setMemberId("tt8");
//        m.setName("테스트8");
//        m.setStockQty(50080);
//        m.setAddrYn("seoul");
//        em.persist(m);
//    }

    @Test
    @Rollback(false)
    void insertInit01() {
        YooSaltbInit01 m = new YooSaltbInit01();
        m.setTenantId("tt2");
        m.setEnplcCd("ee2");
        m.setStoreCd("st");
        m.setItemCd("테스트1");
        m.setStockQty(700000);
        m.setStockAmt(150000);

        repo.save(m);

        // 자동생성된 해당 키값 바로 get
//        Statement.getGeneratedKeys();

        // update
        m.setItemCd("테스트2");
        em.flush();


        // delete
//        YooSaltbInit01 si = repo.find(YooSaltbInit01.class, "id");
//        repo.remove(si);
    }

//    @Test
//    @Rollback(false)
//    void testPersist3() {
//        YooSaltbInit01Hst h = new YooSaltbInit01Hst();
//        YooSaltbInit01 init = new YooSaltbInit01();
//
//        //h.setInitId(22L);
//        h.setSeq(1);
//        h.setEventDscd("I");
//        h.setTenantId("tt2");
//        h.setEnplcCd("ee2");
//        h.setStoreCd("st");
//        h.setItemCd("테스트2");
//        h.setStockQty(700000);
//        h.setStockAmt(150000);
//        h.setSysRegId("yoo");
//        em.persist(h);
//    }

    /**
     * persist entity관리 대상으로 포함된다.
     * transaction 종료 시점에 persist 객체를 DB에 insert 한다.
     */
//    @Test
//    @Rollback(false)
//    void testPersist() {
//        Member m = new Member();
//        m.setMemberId("tt34");
//        m.setName("테스트");
//        em.persist(m);
//    }
//
//
//    /**
//     * persist 관리로 포함시켰다가 다시 때버린다.
//     * commit 시점에는 때버린 상태니 관리목록에 존재 하지 않아 insert를 하지 않는다.
//     */
//    @Test
//    void testDetech() {
//        Member m = new Member();
//        m.setMemberId("test3");
//        m.setName("테스트");
//        em.persist(m);
//        em.detach(m);
//    }
//
//    /**
//     * persist 관리로 포함하고 transaction 끝나기전에 flush로 insert를 한다.
//     * detach로 때어도 이미 insert가 된 상태이고 commit될때 DB에 insert 된다.
//     */
//    @Test
//    void testDetechBeforeFlush() {
//        Member m = new Member();
//        m.setMemberId("tt5");
//        m.setName("테스트");
//        em.persist(m);
//        em.flush();
//        em.detach(m);
//    }
//
//    /**
//     * persist 관리로 들어간후에 Data를 수정하면 update가 된다.
//     * insert into t_member (addr_yn, name, member_id) values (?, ?, ?)
//     * update t_member set addr_yn=?, name=? where member_id=?
//     */
//    @Test
//    void testModify() {
//        Member m = new Member();
//        m.setMemberId("tt6");
//        m.setName("테스트");
//        em.persist(m);
//        m.setName("테스트2");
//    }
//
//    /**
//     * detach된 상태에서 entity를 수정해도 업데이트가 안된다.
//     * detach된 entity를 merge하면 DB에 해당 Data를 다시 불러와 Persist상태로 만들고
//     * merge시에 Persist상태의 객체와 Merge대상 entity의 변경사항을 감지(dirty check) 한후에 변경 값이 있는경우 업데이트 한다.
//     */
//    @Test
//    void testDetechAfterModifyAndMerge() {
//        Member m = new Member();
//        m.setMemberId("tt8");
//        m.setName("테스트");
//        em.persist(m);
//        em.flush();
//        em.detach(m);
//        m.setName("테스트222");
//        em.merge(m);
//    }
//
//
//    /**
//     * select가 몇번 일어날까?
//     */
//    @Test
//    void testPersistAndFind() {
//        Member m = new Member();
//        m.setMemberId("t12");
//        m.setName("테스트");
//        em.persist(m);
//        em.flush();;
//        Member b = em.find(Member.class, "t12");
//        System.out.println(b.getName());
//    }
//
//    @Test
//    void testFind(){
//        Member  m = em.find(Member.class, "test1");
//
//        m.setName("rr33r");
//    }
}
