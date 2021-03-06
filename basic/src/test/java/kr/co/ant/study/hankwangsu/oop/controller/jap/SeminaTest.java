package kr.co.ant.study.hankwangsu.oop.controller.jap;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.ant.study.student.hankwangsu.jpa.compiste.embedded.Company;
import kr.co.ant.study.student.hankwangsu.jpa.compiste.embedded.Employee;
import kr.co.ant.study.student.hankwangsu.jpa.compiste.embedded.Team;
import kr.co.ant.study.student.hankwangsu.jpa.compiste.embedded.key.EmployeeKey;
import kr.co.ant.study.student.hankwangsu.jpa.compiste.embedded.key.TeamKey;
import kr.co.ant.study.student.hankwangsu.jpa.compiste.idclass.ICompany;
import kr.co.ant.study.student.hankwangsu.jpa.compiste.idclass.IEmployee;
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
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	void Student???_??????() {
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
        
        ## Cource?????? ?????? ?????? ????????? ?????????
	 */
	@Test
	void Cource???_??????() {
		Student s = new Student();
		Cource c = new Cource();
		c.addStudent(s);
		manager.persist(s);
		manager.persist(c);
	}
	
	/**
	 * ManyToMany ??????????????? Set??? ????????????
	 */
	@Test
	void Student??????_Cource_??????() {
		Student s = manager.find(Student.class, 1L);
		Cource c = new Cource();
		s.addCource(c);
		manager.persist(s);
		manager.persist(c);
		manager.flush();
		s.removeCource(c);
	}
	
	@Test
	void ?????????() {
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
		
		EmployeeKey empKey2 = new EmployeeKey(3L);
		Employee e2 = new Employee();
		e2.setEmployeeId(empKey2);
		e2.setTeam(t);
		
		manager.persist(e);
		manager.persist(e2);
		manager.flush();
		manager.clear();
		
		Employee result2 = manager.find(Employee.class, empKey);
		log.info("Employee Result 2::: {}", result2);
		Team teamResult = manager.find(Team.class, key);
		log.info("teamResult ::: {}", teamResult);
		Employee result = teamResult.getEmp().get(0);
		log.info("Employee Result 1 ::: {}", result);
		
		log.info("Employee Result 1,2 equals ::: {}", result.equals(result2));
		
	}
	
	@Test
	void ?????????_idClass() {
		ICompany c = new ICompany();
		manager.persist(c);
		
		ITeam t = new ITeam();
		t.setTeamId(13L);
		t.setCompany(c);
		manager.persist(t);
		
		IEmployee e = new IEmployee();
		e.setCompanyId(c.getCompanyId());
		e.setTeamId(t.getTeamId());
		e.setEmployeeId(45L);
		e.setTeam(t);
		manager.persist(e);
		
	}
	
	

}
