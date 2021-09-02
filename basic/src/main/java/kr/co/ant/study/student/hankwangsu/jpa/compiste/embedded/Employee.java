package kr.co.ant.study.student.hankwangsu.jpa.compiste.embedded;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import kr.co.ant.study.student.hankwangsu.jpa.compiste.embedded.key.EmployeeKey;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @Table
@Getter @Setter
public class Employee {

	@EmbeddedId
	private EmployeeKey employeeId;

	@ManyToOne
	@MapsId("teamKey")
	@JoinColumns({
		@JoinColumn(name="team_id", referencedColumnName = "t_team_id"),
		@JoinColumn(name="company_id", referencedColumnName = "t_company_id")
	})
	@ToString.Exclude
	private Team team;
}
