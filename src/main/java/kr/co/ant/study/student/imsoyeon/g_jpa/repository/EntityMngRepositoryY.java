package kr.co.ant.study.student.imsoyeon.g_jpa.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ant.study.student.imsoyeon.g_jpa.entity.InitEntityY;
import kr.co.ant.study.student.imsoyeon.g_jpa.entity.InitHstEntityY;

@Repository
public class EntityMngRepositoryY {

	@Autowired
	private EntityManager em;
	
	public void save(InitEntityY init01) {
		
		em.persist(init01);
	}
}
