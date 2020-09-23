package kr.co.ant.study.student.moonjonghun.jpa;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ant.study.student.moonjonghun.jpa.DAO.MoonMember;

@Repository
public class MoonMemberRepository {
	
	@Autowired
	private EntityManager em;
	
	public void save(MoonMember member) {
		em.persist(member);
		em.flush();
	}
	
	public void update(MoonMember member) {
		em.merge(member);
	}
	
}
