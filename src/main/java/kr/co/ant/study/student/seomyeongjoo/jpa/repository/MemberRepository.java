package kr.co.ant.study.student.seomyeongjoo.jpa.repository;

import kr.co.ant.study.student.seomyeongjoo.jpa.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MemberRepository {
    @Autowired
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }
}
