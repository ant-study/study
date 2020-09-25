package kr.co.ant.study.student.hankwangsu.jpa.compiste.idclass;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import kr.co.ant.study.student.hankwangsu.jpa.compiste.embedded.key.EmployeeKey;
import kr.co.ant.study.student.hankwangsu.jpa.compiste.idclass.key.IEmployeeKey;
import lombok.Getter;
import lombok.Setter;

//@Entity @Table
@Getter @Setter
@IdClass(IEmployeeKey.class)
public class IEmployee {

	@Id
	private Long companyId;
	
	@Id
	private Long teamId;
	
	@Id
	private Long employeeId;

	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="team_id"),
		@JoinColumn(name="company_id")
	})
	private ITeam team;
}
