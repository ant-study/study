package kr.co.ant.study.student.hankwangsu.jpa.compiste.embedded;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kr.co.ant.study.student.hankwangsu.jpa.compiste.embedded.key.TeamKey;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity @Table
@Getter @Setter @ToString
public class Team {

	@EmbeddedId
	TeamKey key;
	
	@ManyToOne
	@MapsId("companyId")
	@JoinColumn(name="t_company_id")
	private Company company;

	@OneToMany(mappedBy = "team")
	private List<Employee> emp = new ArrayList<>();
	
}
