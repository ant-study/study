package kr.co.ant.study.hankwangsu.oop.controller.jap;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import kr.co.ant.study.student.hankwangsu.jpa.compiste.embedded.Company;
import kr.co.ant.study.student.hankwangsu.jpa.compiste.embedded.Employee;
import kr.co.ant.study.student.hankwangsu.jpa.compiste.embedded.Team;
import kr.co.ant.study.student.hankwangsu.jpa.compiste.embedded.key.EmployeeKey;
import kr.co.ant.study.student.hankwangsu.jpa.compiste.embedded.key.TeamKey;
import kr.co.ant.study.student.hankwangsu.jpa.compiste.idclass.ICompany;
import kr.co.ant.study.student.hankwangsu.jpa.compiste.idclass.ITeam;
import kr.co.ant.study.student.hankwangsu.jpa.compiste.idclass.key.ITeamKey;
import kr.co.ant.study.student.hankwangsu.jpa.many.Cource;
import kr.co.ant.study.student.hankwangsu.jpa.many.Student;
import lombok.extern.slf4j.Slf4j;

@DataJpaTest
@EntityScan(basePackages = "kr.co.ant.study.student.hankwangsu.jpa")
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("hks")
@Rollback(false)
@Slf4j
class SeminaTest {

	@Autowired
	private EntityManager manager;
	
	/**
	 * insert into student (id) values (?)
	 * insert into cource (id) values (?)
	 * insert into cource_like (student_id, cource_id) values (?, ?)
	 */
	@Test
	void Student에_저장() {
		Student s = new Student();
		Cource c = new Cource();
		s.addCource(c);
		manager.persist(s);
		manager.persist(c);
	}
	
	/**
	 * insert into student (id) values (?)
	 * -----------------------    
        insert into cource (id) values (?)
        
        ## Cource에도 맵핑 걸면 똑같이 저장됨
	 */
	@Test
	void Cource에_저장() {
		Student s = new Student();
		Cource c = new Cource();
		c.addStudent(s);
		manager.persist(s);
		manager.persist(c);
	}
	
	@Test
	void 복합키() {
		Company c = new Company();
		manager.persist(c);
		
		TeamKey key = new TeamKey();
		key.setTeamId(11L);
		Team t = new Team();
		t.setKey(key);
		t.setCompany(c);
		manager.persist(t);
		
		EmployeeKey empKey = new EmployeeKey(2L);
		Employee e = new Employee();
		e.setEmployeeId(empKey);
		e.setTeam(t);
		manager.persist(e);
		manager.flush();
		manager.clear();
		
		Employee result = manager.find(Employee.class, empKey);
		log.info("Employee ::: {}", result);
		
		
		Team teamResult = manager.find(Team.class, key);
		log.info("teamResult ::: {}", teamResult);
		
	}
	
	@Test
	void 복합키_idClass() {
		ICompany c = new ICompany();
		manager.persist(c);
		
		ITeam t = new ITeam();
		t.setTeamId(11L);
		t.setCompanyId(c.getCompanyId());
		t.setCompany(c);
		manager.persist(t);
		
		/*EmployeeKey empKey = new EmployeeKey(2L);
		Employee e = new Employee();
		e.setEmployeeId(empKey);
		manager.persist(e);
		manager.flush();
		manager.clear();
		
		Employee result = manager.find(Employee.class, empKey);
		log.info("Employee ::: {}", result);*/
		
		
		/*Team teamResult = manager.find(Team.class, key);
		log.info("teamResult ::: {}", teamResult);*/
		
	}
	
	

}
