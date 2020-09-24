/**
 * Author : yooS
 * Create Date : 2020. 9. 23.
 */
package kr.co.ant.study.student.songyoona.jpa;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ant.study.student.songyoona.jpa.domain.YooMember;

/**
 * @description :
 * @author : yooS
 * @createDate : 2020. 9. 23.
 */
@Repository
public class YooMemberRepository {

    @Autowired
    private EntityManager em;


    public void save(YooMember member) {
        em.persist(member);
    }

}
