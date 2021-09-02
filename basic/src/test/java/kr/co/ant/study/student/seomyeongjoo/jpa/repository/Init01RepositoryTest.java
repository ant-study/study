package kr.co.ant.study.student.seomyeongjoo.jpa.repository;

import kr.co.ant.study.student.seomyeongjoo.jpa.domain.Init01;
import kr.co.ant.study.student.seomyeongjoo.jpa.domain.Init01History;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@EntityScan(basePackages = "kr.co.ant.study.student.seomyeongjoo.jpa")
@DataJpaTest //JPA 관련 설정 로딩
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //Test Database 사용안함
@Import({Init01Repository.class}) //Repository Annotation은 Jpa 관련 설정이 아니라 DataJpaTest시에 Bean으로 등록 되지 않기때문에 Import로 강제 등록
@ActiveProfiles("juu") //자신이 설정한 application-이니셜.yml을 로드 하기 위해 Profile 설정
@Rollback(false)
class Init01RepositoryTest {
    @Autowired
    private Init01Repository repository;

    @Autowired
    private EntityManager em;

    @Test
    void all(){
        insert();
        update();
        delete();

    }

    @Test
    void insert(){
        Init01 init01 = new Init01();
        Init01History hst = new Init01History();

        init01.setTenantId("T431");
        init01.setEnplcCd("1234");
        init01.setStockAmt(new BigDecimal("12.123"));

        em.persist(init01);

        hst.setEventDscd("I");
        //hst.setInit01(init01);
        hst.setInitId(init01.getIdx());

        init01.addInit01Hst(hst);
        repository.save(init01);

    }

    @Test
    void update(){
        Init01 init01 = repository.find(4L);
        Init01History hst = new Init01History();

        init01.setStockAmt(new BigDecimal("112345.88"));
        hst.setEventDscd("U");
        //hst.setInit01(init01);
        hst.setInitId(init01.getIdx());
        init01.addInit01Hst(hst);
        repository.save(init01);

    }

    @Test
    void delete(){
        Init01 init01 = repository.find(6L);
        Init01History hst = new Init01History();
        hst.setEventDscd("D");
        //hst.setInit01(init01);
        hst.setInitId(init01.getIdx());
        init01.addInit01Hst(hst);
        repository.delete(init01);

    }
}