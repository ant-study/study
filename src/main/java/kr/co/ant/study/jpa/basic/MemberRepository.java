package kr.co.ant.study.jpa.basic;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ant.study.jpa.basic.domain.Member;

@Repository
public class MemberRepository {

	@Autowired
	private EntityManager em;
	
	
	public void save(Member member) {
		em.persist(member);
	}
}
