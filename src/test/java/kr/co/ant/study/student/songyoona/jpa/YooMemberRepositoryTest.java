/**
 * Author : yooS
 * Create Date : 2020. 9. 23.
 */
package kr.co.ant.study.student.songyoona.jpa;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import kr.co.ant.study.student.songyoona.jpa.domain.YooMember;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 23.
 */
@DataJpaTest //JPA 관련 설정 로딩
@AutoConfigureTestDatabase(replace = Replace.NONE) //Test Database 사용안함
@Import(YooMemberRepository.class) //Repository Annotation은 Jpa 관련 설정이 아니라 DataJpaTest시에 Bean으로 등록 되지 않기때문에 Import로 강제 등록
@ActiveProfiles("yoo") //자신이 설정한 application-이니셜.yml을 로드 하기 위해 Profile 설정
@Rollback(false) //기본 Rollback으로 되어 있음 Rollback true이면 commit시 생성하는 쿼리가 실행되지 않기 때문에 공부할때는 귀찮아도 false로 해서 테스트
class YooMemberRepositoryTest {

    @Autowired
    private EntityManager manager;


    @Test
    @Rollback(false)
    void testPersist() {
        YooMember m = new YooMember();
        m.setMemberId("tt");
        m.setName("테스트");
        m.setDate("20200923");
        manager.persist(m);
    }

}
