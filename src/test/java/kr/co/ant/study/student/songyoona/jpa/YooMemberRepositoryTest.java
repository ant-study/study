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

import kr.co.ant.study.student.songyoona.jpa.domain.YooMember;
import kr.co.ant.study.student.songyoona.jpa.domain.YooSaltbInit01;
import kr.co.ant.study.student.songyoona.jpa.domain.YooSaltbInit01Hst;

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
    private EntityManager manager;

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
//        manager.persist(m);
//    }

    @Test
    @Rollback(false)
    void testPersist2() {
        YooSaltbInit01 m = new YooSaltbInit01();
        m.setTenantId("tt2");
        m.setEnplcCd("ee2");
        m.setStoreCd("st");
        m.setItemCd("테스트2");
        m.setStockQty(700000);
        m.setStockAmt(150000);
        m.setSysRegId("yoo");
        //repo.save(m);

        YooSaltbInit01Hst h = new YooSaltbInit01Hst();
        h.setSeq(1);
        h.setEventDscd("I");
        h.setTenantId("tt2");
        h.setEnplcCd("ee2");
        h.setStoreCd("st");
        h.setItemCd("테스트2");
        h.setStockQty(700000);
        h.setStockAmt(150000);
        h.setSysRegId("yoo");
//        h.setTenantId(m.getTenantId());
//        h.setEnplcCd(m.getEnplcCd());
//        h.setStoreCd(m.getStoreCd());
//        h.setItemCd(m.getItemCd());
//        h.setStockQty(m.getStockQty());
//        h.setStockAmt(m.getStockAmt());
//        h.setSysRegId(m.getSysRegId());

        m.addYooInitHst(h);
        repo.save(m);


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
//        manager.persist(h);
//    }
}
