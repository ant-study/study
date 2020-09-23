package kr.co.ant.study.student.seomyeongjoo.jpa.repository;

import kr.co.ant.study.student.seomyeongjoo.jpa.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
//동일한 Entity명이 있는경우 에러나기 때문에 본인의 package 경로로만 entity를 scan하게 설정 한다.
@EntityScan(basePackages = "kr.co.ant.study.student.seomyeongjoo.jpa")
@DataJpaTest //JPA 관련 설정 로딩
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //Test Database 사용안함
@Import({MemberRepository.class}) //Repository Annotation은 Jpa 관련 설정이 아니라 DataJpaTest시에 Bean으로 등록 되지 않기때문에 Import로 강제 등록
@ActiveProfiles("juu") //자신이 설정한 application-이니셜.yml을 로드 하기 위해 Profile 설정
@Rollback(false)
class MemberRepositoryTestJuu {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private EntityManager manager;

    @Test
    void testPersist() {
        Member m = new Member("명주", 29);
        manager.persist(m);
    }
}