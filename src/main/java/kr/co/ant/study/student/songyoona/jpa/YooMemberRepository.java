/**
 * Author : yooS
 * Create Date : 2020. 9. 23.
 */
package kr.co.ant.study.student.songyoona.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ant.study.student.songyoona.jpa.domain.YooMember;
import kr.co.ant.study.student.songyoona.jpa.domain.YooSaltbInit01;
import kr.co.ant.study.student.songyoona.jpa.domain.YooSaltbInit01Hst;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 23.
 */
@Repository
public class YooMemberRepository {

    @Autowired
    private EntityManager em;

    public <T> void save(T t) {
        em.persist(t);
    }

//    public void save(YooMember member) {
//        em.persist(member);
//    }
//
//    public void save(YooSaltbInit01 saltbInit) {
//        em.persist(saltbInit);
//    }
//    public void save(YooSaltbInit01Hst saltbInitHst) {
//        em.persist(saltbInitHst);
//    }

    // init01 테이블에 데이터가 들어오면 initHst 테이블에 insert가 일어난다 (trigger)

}
