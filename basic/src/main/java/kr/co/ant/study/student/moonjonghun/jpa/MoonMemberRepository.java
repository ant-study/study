package kr.co.ant.study.student.moonjonghun.jpa;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MoonMemberRepository {
	
	@Autowired
	private EntityManager em;
	
	//저장
	public <T> void save(T member) throws Exception{
		//entity instance를 가져와서 context에 추가하고 지속적으로 instance를 관리할 수 있음
		em.persist(member);
	}
	
	//수정
	public <T> void update(T member) {
		em.merge(member);
	}
	
	//조회
	public <T, U> Object select(T data, U pk) {
		return em.find(data.getClass(), pk);
	}
	
	//삭제
	public <T> void removeData(T data) {
		//단순 삭제쿼리
		em.remove(data);
	}
	
}
