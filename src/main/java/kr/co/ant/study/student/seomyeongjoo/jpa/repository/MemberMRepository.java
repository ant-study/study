package kr.co.ant.study.student.seomyeongjoo.jpa.repository;


import kr.co.ant.study.student.seomyeongjoo.jpa.domain.Member_m;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MemberMRepository {
    @Autowired
    private EntityManager em;

    public void save(Member_m member){
        em.persist(member);
    }
}
